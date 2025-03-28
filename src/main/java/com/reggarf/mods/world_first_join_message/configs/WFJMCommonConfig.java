//package com.reggarf.mods.world_first_join_message.configs;
//
//import com.electronwill.nightconfig.core.file.CommentedFileConfig;
//import com.electronwill.nightconfig.core.io.WritingMode;
//import net.minecraftforge.common.ForgeConfigSpec;
//import net.minecraftforge.fml.common.Mod;
//
//@Mod.EventBusSubscriber
//public class WFJMCommonConfig {
//
//    public static final String welcome_message = "welcome_Message";
//    public static ForgeConfigSpec.BooleanValue enabled;
//    public static ForgeConfigSpec.ConfigValue<String> welcomeMessage;
//    public static ForgeConfigSpec.ConfigValue<String> clickableUrl;
//    public static ForgeConfigSpec.ConfigValue<String> welcomeMessageColor;
//    public static ForgeConfigSpec.ConfigValue<String> clickableTextColor;
//
//
//
//    public WFJMCommonConfig(ForgeConfigSpec.Builder builder) {
//
//        builder.comment("General Settings").push(welcome_message);
//        enabled = builder
//                .comment("Enable or disable the welcome message")
//                .define("enabled", true);
//
//        welcomeMessage = builder
//                .comment("The message to be displayed when a player joins the game for the first time")
//                .define("text", "THANKS TO OUR SPONSORS, ZAP-HOSTING! Support Reggarf And Save BIG - USE CODE Reggarf-1047 FOR 20% OFF Your ORDER!");
//
//        welcomeMessageColor = builder
//                .comment("The RGB color code for the welcome message.",
//                        "Examples: 'FF0000' for red, '00FF00' for green, '0000FF' for blue",
//                        "'FFFF00' for yellow, 'FF00FF' for magenta, '00FFFF' for cyan")
//                .define("color", "FFFFFF"); // Default color (white)
//
//        clickableUrl = builder
//                .comment("The URL to be opened when the player clicks the link")
//                .define("url", "https://zap-hosting.com/reggarf");
//
//        clickableTextColor = builder
//                .comment("The RGB color code for the clickable text '[Click here]',",
//                        "Examples: 'FF0000' for red, '00FF00' for green, '0000FF' for blue",
//                        "'FFFF00' for yellow, 'FF00FF' for magenta, '00FFFF' for cyan, 'FFFFFF' for white")
//                .define("clickableColor", "00FFAA"); // Default color (cyan)
//
//        builder.pop();
//
//    }
//    public static void loadConfig(ForgeConfigSpec spec, java.nio.file.Path path) {
//        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
//                .sync()
//                .autosave()
//                .writingMode(WritingMode.REPLACE)
//                .build();
//        configData.load();
//        spec.setConfig(configData);
//    }
//}
//
