package com.reggarf.mods.world_first_join_message.join;


import com.reggarf.mods.world_first_join_message.World_First_Join_Message;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = World_First_Join_Message.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FirstJoinMessageHandler {

    // Configuration keys
    private static ForgeConfigSpec.BooleanValue enabled;
    private static ForgeConfigSpec.ConfigValue<String> welcomeMessage;
    private static ForgeConfigSpec.ConfigValue<String> clickableUrl;
    private static ForgeConfigSpec.ConfigValue<String> welcomeMessageColor;
    private static ForgeConfigSpec.ConfigValue<String> clickableTextColor;

    // Configuration setup
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("welcome_message");

        enabled = builder
                .comment("Enable or disable the welcome message")
                .define("enabled", true);

        welcomeMessage = builder
                .comment("The message to be displayed when a player joins the game for the first time")
                .define("text", "THANKS TO OUR SPONSORS, ZAP-HOSTING! Support Reggarf And Save BIG - USE CODE Reggarf-1047 FOR 20% OFF Your ORDER!");

        welcomeMessageColor = builder
                .comment("The RGB color code for the welcome message.",
                        "Examples: 'FF0000' for red, '00FF00' for green, '0000FF' for blue",
                        "'FFFF00' for yellow, 'FF00FF' for magenta, '00FFFF' for cyan")
                .define("color", "FFFFFF"); // Default color (white)

        clickableUrl = builder
                .comment("The URL to be opened when the player clicks the link")
                .define("url", "https://zap-hosting.com/reggarf");

        clickableTextColor = builder
                .comment("The RGB color code for the clickable text '[Click here]',",
                        "Examples: 'FF0000' for red, '00FF00' for green, '0000FF' for blue",
                        "'FFFF00' for yellow, 'FF00FF' for magenta, '00FFFF' for cyan, 'FFFFFF' for white")
                .define("clickableColor", "00FFAA"); // Default color (cyan)

        builder.pop();
        COMMON_SPEC = builder.build();
    }

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
