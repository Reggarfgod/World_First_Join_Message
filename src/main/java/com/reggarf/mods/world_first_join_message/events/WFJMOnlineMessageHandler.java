package com.reggarf.mods.world_first_join_message.events;

import com.reggarf.mods.world_first_join_message.WFJMessage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WFJMessage.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WFJMOnlineMessageHandler {
    private static final String MESSAGE_URL = WFJMessage.CONFIG.common.OnlineMessageURL; // Change this to your GitHub URL for the message
    private static final String URL_FETCH_URL = WFJMessage.CONFIG.common.onlineurl; // URL for the clickable URL
    private static String lastFetchedMessage = null; // Store last fetched message at mod level
    private static String clickableURL = "https://www.youractualurl.com"; // Default URL if fetching fails

    // mod class on startup
    public static void initializeMod() {
        lastFetchedMessage = WFJMOnlineMessageFetcher.fetchOnlineMessage(MESSAGE_URL);
        clickableURL = fetchClickableURL(URL_FETCH_URL); // Fetch the clickable URL
        System.out.println("[WorldFirstJoinMessage] Fetched startup message: " + lastFetchedMessage);
        System.out.println("[WorldFirstJoinMessage] Fetched clickable URL: " + clickableURL);
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        // Fetch the latest message
        String latestMessage = WFJMOnlineMessageFetcher.fetchOnlineMessage(MESSAGE_URL);
        if (latestMessage.isEmpty()) return; // If message fails to load, do nothing

        // Retrieve stored data from the player
        CompoundTag playerData = player.getPersistentData();
        String lastSeenMessage = playerData.getString("lastSeenMessage");

        // Compare fetched message with both last stored and player's last seen message
        if (lastFetchedMessage == null || hasMessageChanged(latestMessage, lastFetchedMessage)) {
            lastFetchedMessage = latestMessage; // Update the stored message on every check
        }

        if (hasMessageChanged(latestMessage, lastSeenMessage)) {
            Component message = createClickableMessage(latestMessage);
            player.sendSystemMessage(message);

            // Store the new message so the player doesn't see it again until it changes
            playerData.putString("lastSeenMessage", latestMessage);
        }
    }

    // AI-Based Levenshtein Distance to check every character change
    private static boolean hasMessageChanged(String newMessage, String oldMessage) {
        if (oldMessage == null || oldMessage.isEmpty()) return true; // Consider changed if no old message exists

        int newLen = newMessage.length();
        int oldLen = oldMessage.length();
        int[][] dp = new int[newLen + 1][oldLen + 1];

        for (int i = 0; i <= newLen; i++) {
            for (int j = 0; j <= oldLen; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int cost = (newMessage.charAt(i - 1) == oldMessage.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(Math.min(
                                    dp[i - 1][j] + 1,      // Deletion
                                    dp[i][j - 1] + 1),     // Insertion
                            dp[i - 1][j - 1] + cost // Substitution
                    );
                }
            }
        }
        return dp[newLen][oldLen] > 0; // If edit distance is greater than 0, the message has changed
    }

    private static Component createClickableMessage(String messageText) {
        TextColor textColor = TextColor.fromRgb(0xFFFFFF); // Default to white
        TextColor clickableTextColor = TextColor.fromRgb(0x00FF00); // Green for clickable text

        return Component.literal(messageText)
                .setStyle(Style.EMPTY.withColor(textColor)) // Main message color
                .append(" ")
                .append(Component.literal("[Click Here]")
                        .setStyle(Style.EMPTY
                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, clickableURL)) // Use the fetched URL
                                .withUnderlined(true)
                                .withColor(clickableTextColor) // Clickable text color
                        ));
    }

    // Method to fetch the clickable URL from GitHub or another source
    private static String fetchClickableURL(String url) {
        // get the URL from GitHub or a server.
        String fetchedURL = WFJMOnlineMessageFetcher.fetchOnlineMessage(url); // Fetch the URL content
        return (fetchedURL != null && !fetchedURL.isEmpty()) ? fetchedURL : clickableURL; // Default to the static URL if fetching fails
    }
}
