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

    private static final String MESSAGE_URL = "https://raw.githubusercontent.com/Reggarfgod/World_First_Join_Message/refs/heads/CC/1.21.1/forge/messages.txt"; // Change this to your GitHub URL

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        // Fetch the latest message from GitHub
        String latestMessage = WFJMOnlineMessageFetcher.fetchOnlineMessage(MESSAGE_URL);
        if (latestMessage.isEmpty()) return; // If the message fails to load, do nothing

        // Retrieve stored data from the player
        CompoundTag playerData = player.getPersistentData();
        String lastSeenMessage = playerData.getString("lastSeenMessage");

        // Compare messages character by character
        if (hasMessageChanged(latestMessage, lastSeenMessage)) {
            Component message = createClickableMessage(latestMessage);
            player.sendSystemMessage(message);

            // Store the new message so the player doesn't see it again until it changes
            playerData.putString("lastSeenMessage", latestMessage);
        }
    }


    //TODO:future me change method to check new message

    // Method to compare messages character by character
    private static boolean hasMessageChanged(String newMessage, String oldMessage) {
        // Check if lengths are different
        if (newMessage.length() != oldMessage.length()) {
            return true; // If lengths are different, the message has changed
        }

        // Check each character individually
        for (int i = 0; i < newMessage.length(); i++) {
            if (newMessage.charAt(i) != oldMessage.charAt(i)) {
                return true; // If any character is different, the message has changed
            }
        }

        return false; // No change detected
    }

    private static Component createClickableMessage(String messageText) {
        TextColor textColor = TextColor.fromRgb(0xFFFFFF); // Default to white
        TextColor clickableTextColor = TextColor.fromRgb(0x00FF00); // Green for clickable text

        return Component.literal(messageText)
                .setStyle(Style.EMPTY.withColor(textColor)) // Main message color
                .append(" ")
                .append(Component.literal("[Click Here]")
                        .setStyle(Style.EMPTY
                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://your-link.com"))
                                .withUnderlined(true)
                                .withColor(clickableTextColor) // Clickable text color
                        ));
    }
}