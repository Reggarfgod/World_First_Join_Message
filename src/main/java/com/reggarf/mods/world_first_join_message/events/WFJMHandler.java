package com.reggarf.mods.world_first_join_message.events;


import com.reggarf.mods.world_first_join_message.World_First_Join_Message;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.reggarf.mods.world_first_join_message.config.WFJMCommonConfig.*;

@Mod.EventBusSubscriber(modid = World_First_Join_Message.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WFJMHandler {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && enabled.get()) {
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
        String textColorCode = welcomeMessageColor.get();
        TextColor textColor = TextColor.parseColor("#" + textColorCode);

        String clickableColorCode = clickableTextColor.get();
        TextColor clickableTextColor = TextColor.parseColor("#" + clickableColorCode);

        return Component.literal(welcomeMessage.get())
                .setStyle(Style.EMPTY.withColor(textColor)) // Main message color
                .append(" ")
                .append(Component.literal("[Click here]")
                        .setStyle(Style.EMPTY
                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, clickableUrl.get()))
                                .withUnderlined(true)
                                .withColor(clickableTextColor) // Clickable text color
                        ));
    }
}
