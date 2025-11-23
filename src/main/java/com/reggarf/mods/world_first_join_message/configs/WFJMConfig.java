package com.reggarf.mods.world_first_join_message.configs;


import com.reggarf.mods.better_lib.config.annotation.Config;
import com.reggarf.mods.better_lib.config.annotation.ConfigEntry;
import com.reggarf.mods.better_lib.config.api.ConfigData;
import com.reggarf.mods.world_first_join_message.WFJMessage;

@Config(name = WFJMessage.MOD_ID, background = "minecraft:textures/block/diamond_block.png")
public class WFJMConfig implements ConfigData {

        @ConfigEntry.Category("General Settings")
        @ConfigEntry.Description("Enable or disable the welcome message")
        public Boolean enabled = true;

        @ConfigEntry.Category("General Settings")
        @ConfigEntry.Description("The message to be displayed when a player joins the game for the first time")
        public String welcomeMessage = "THANKS TO OUR SPONSORS, ZAP-HOSTING! Support Reggarf And Save BIG - USE CODE Reggarf-1047 FOR 20% OFF Your ORDER!";

        @ConfigEntry.Category("Chat")
        @ConfigEntry.Description("The RGB color code for the welcome message.")
        @ConfigEntry.ColorField
        public int welcomeMessageColor = 0xFFFFFF;

        @ConfigEntry.Category("General Settings")
        @ConfigEntry.Description("The URL to be opened when the player clicks the link")
        public String clickableUrl = "https://zap-hosting.com/reggarf";

        @ConfigEntry.Category("General Settings")
        @ConfigEntry.Description("clickable text")
        public String clickhere = "[Click here]";

        @ConfigEntry.Category("Chat")
        @ConfigEntry.Description("The RGB color code for the clickable text '[Click here]")
        @ConfigEntry.ColorField
        public int clickableTextColor = 0x00FFAA;
}
