package com.reggarf.mods.world_first_join_message.configs;



import com.reggarf.mods.world_first_join_message.WFJMessage;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = WFJMessage.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/mossy_cobblestone.png")
public class WFJMConfig extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("common")
    @ConfigEntry.Gui.TransitiveObject()
    public Common common = new Common();

//    @ConfigEntry.Category("client")
//    @ConfigEntry.Gui.TransitiveObject()
//    public Client client = new Client();

    @Config(name = "common")
    public static final class Common implements ConfigData {

        @ConfigEntry.Gui.Tooltip
        @Comment("Enable or disable the welcome message")
        public Boolean enabled = true;

        @ConfigEntry.Gui.Tooltip
        @Comment("The message to be displayed when a player joins the game for the first time")
        public String welcomeMessage = "THANKS TO OUR SPONSORS, ZAP-HOSTING! Support Reggarf And Save BIG - USE CODE Reggarf-1047 FOR 20% OFF Your ORDER!";

        @ConfigEntry.Gui.Tooltip
        @Comment("The RGB color code for the welcome message. " +
                "Examples: '#FF0000' for red, '#00FF00' for green, " +
                "'#0000FF' for blue '#FFFF00' for yellow, '#FF00FF' for magenta, '#00FFFF' for cyan")
        public String welcomeMessageColor = "#FFFFFF";

        @ConfigEntry.Gui.Tooltip
        @Comment("The URL to be opened when the player clicks the link")
        public String clickableUrl = "https://zap-hosting.com/reggarf";

        @ConfigEntry.Gui.Tooltip
        @Comment("clickable text")
        public String clickhere = "[Click here]";

        @ConfigEntry.Gui.Tooltip
        @Comment("The RGB color code for the clickable text '[Click here]'"+
                "Examples: '#FF0000' for red, '#00FF00' for green, '#0000FF' for blue"+
                "'#FFFF00' for yellow, '#FF00FF' for magenta, '#00FFFF' for cyan, '#FFFFFF' for white")
        public String clickableTextColor = "#00FFAA";



    }

//    @Config(name = "client")
//    public static final class Client implements ConfigData {
//
//        @ConfigEntry.Gui.Tooltip
//        @Comment("models")
//        public boolean showFirstPerson = true;
//
//    }
}
