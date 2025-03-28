package com.reggarf.mods.world_first_join_message.events;


import com.reggarf.mods.world_first_join_message.WFJMessage;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;



//@EventBusSubscriber(modid = WFJMessage.MOD_ID ,bus = EventBusSubscriber.Bus.MOD)
public class WFJMHandler {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && WFJMessage.CONFIG.common.enabled) {
            // Check if the player is joining for the first time
            if (isFirstJoin(player)) {
                Component message = createClickableMessage();
                player.sendSystemMessage(message);
                markPlayerAsJoined(player);
            }
        }
    }

    private static boolean isFirstJoin(ServerPlayer player) {
        // Example logic: Use persistent data to track if the player has joined before
        return !player.getPersistentData().getBoolean("hasJoinedBefore");
    }

    private static void markPlayerAsJoined(ServerPlayer player) {
        // Mark the player as having joined before
        player.getPersistentData().putBoolean("hasJoinedBefore", true);
    }

    private static Component createClickableMessage() {
        // Parse the RGB colors from configuration
        TextColor textColor = parseTextColor(WFJMessage.CONFIG.common.welcomeMessageColor);
        TextColor clickableTextColor = parseTextColor(WFJMessage.CONFIG.common.clickableTextColor);

        return Component.literal(WFJMessage.CONFIG.common.welcomeMessage)
                .setStyle(Style.EMPTY.withColor(textColor)) // Main message color
                .append(" ")
                .append(Component.literal(WFJMessage.CONFIG.common.clickhere)
                        .setStyle(Style.EMPTY
                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, WFJMessage.CONFIG.common.clickableUrl))
                                .withUnderlined(true)
                                .withColor(clickableTextColor) // Clickable text color
                        ));
    }

    private static TextColor parseTextColor(String hex) {
        try {
            if (hex.startsWith("#")) {
                hex = hex.substring(1); // Remove '#' if present
            }
            int rgb = Integer.parseInt(hex, 16); // Convert hex to int
            return TextColor.fromRgb(rgb);
        } catch (NumberFormatException e) {
            System.err.println("Invalid color format: " + hex);
            return TextColor.fromRgb(0xFFFFFF); // Default to white if invalid
        }
    }
}