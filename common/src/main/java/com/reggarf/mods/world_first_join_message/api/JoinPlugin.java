package com.reggarf.mods.world_first_join_message.api;


import com.reggarf.mods.better_lib.message.api.JoinMessagePlugin;
import com.reggarf.mods.better_lib.message.api.JoinMessagePlugins;
import com.reggarf.mods.better_lib.message.api.JoinMessageSet;
import com.reggarf.mods.world_first_join_message.Constants;

import java.util.List;

import static com.reggarf.mods.world_first_join_message.Constants.MOD_ID;

public class JoinPlugin implements JoinMessagePlugin {
    @Override
    public String getModId() {
        return MOD_ID;
    }

    @Override
    public boolean enabled() {
        return Constants.CONFIG.enabled;
    }

    @Override
    public List<JoinMessageSet> getMessageSets() {
        return List.of(
                new JoinMessageSet()
                        .addBlankLine()
                        .addText(Constants.CONFIG.welcomeMessage, Constants.CONFIG.welcomeMessageColor)
                        .addLink(Constants.CONFIG.clickhere, Constants.CONFIG.clickableUrl, Constants.CONFIG.clickableTextColor, ""));
    }

    public static void register() {
        JoinMessagePlugins.register(new JoinPlugin());
    }
}
