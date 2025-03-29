package com.reggarf.mods.world_first_join_message.events;

import com.reggarf.mods.world_first_join_message.WFJMessage;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

import static net.minecraft.text.TextColor.fromRgb;

public class WFJMHandler {

    public static void register() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            if (WFJMessage.CONFIG.common.enabled) {
                if (isFirstJoin(player)) {
                    Text message = createClickableMessage();
                    player.sendMessage(message, false);
                    markPlayerAsJoined(player);
                }
            }
        });
    }

    private static boolean isFirstJoin(ServerPlayerEntity player) {
        return !player.getCommandTags().contains("hasJoinedBefore");
    }

    private static void markPlayerAsJoined(ServerPlayerEntity player) {
        if (!player.getCommandTags().contains("hasJoinedBefore")) {
            player.addCommandTag("hasJoinedBefore");
        }
    }


    private static Text createClickableMessage() {
        TextColor textColor = parseTextColor(WFJMessage.CONFIG.common.welcomeMessageColor);
        TextColor clickableTextColor = parseTextColor(WFJMessage.CONFIG.common.clickableTextColor);
         //int color
        //TextColor textColor = fromRgb(WFJMessage.CONFIG.common.welcomeMessageColor);
        //TextColor clickableTextColor = fromRgb(WFJMessage.CONFIG.common.clickableTextColor);
        return Text.literal(WFJMessage.CONFIG.common.welcomeMessage)
                .setStyle(Style.EMPTY.withColor(textColor))
                .append(" ")
                .append(Text.literal(WFJMessage.CONFIG.common.clickhere)
                        .setStyle(Style.EMPTY
                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, WFJMessage.CONFIG.common.clickableUrl))
                                .withUnderline(true)
                                .withColor(clickableTextColor)
                        ));
    }

    private static TextColor parseTextColor(String hex) {
        try {
            if (hex.startsWith("#")) {
                hex = hex.substring(1); // Remove '#' if present
            }
            int rgb = Integer.parseInt(hex, 16); // Convert hex to int
            return fromRgb(rgb);
        } catch (NumberFormatException e) {
            System.err.println("Invalid color format: " + hex);
            return fromRgb(0xFFFFFF); // Default to white if invalid
        }
    }
}
