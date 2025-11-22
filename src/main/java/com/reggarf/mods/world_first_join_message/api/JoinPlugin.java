package com.reggarf.mods.world_first_join_message.api;

import com.reggarf.mods.better_lib.Better_lib;
import com.reggarf.mods.better_lib.message.api.JoinMessagePlugin;
import com.reggarf.mods.better_lib.message.api.JoinMessagePlugins;
import com.reggarf.mods.better_lib.message.event.JoinMessageSet;
import com.reggarf.mods.world_first_join_message.WFJMessage;

import java.util.List;

public class JoinPlugin implements JoinMessagePlugin {
    @Override
    public String getModId() {
        return Better_lib.MODID;
    }

    @Override
    public boolean enabled() {
        return WFJMessage.CONFIG.enabled;
    }

    @Override
    public List<JoinMessageSet> getMessageSets() {
        return List.of(
                new JoinMessageSet()
                        .addBlankLine()
                        .addText(WFJMessage.CONFIG.welcomeMessage, WFJMessage.CONFIG.welcomeMessageColor)
                        .addLink(WFJMessage.CONFIG.clickhere, WFJMessage.CONFIG.clickableUrl, WFJMessage.CONFIG.clickableTextColor, ""));
    }

    public static void register() {
        JoinMessagePlugins.register(new JoinPlugin());
    }
}
