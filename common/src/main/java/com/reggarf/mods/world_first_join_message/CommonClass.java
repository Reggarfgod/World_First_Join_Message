package com.reggarf.mods.world_first_join_message;


import com.reggarf.mods.better_lib.config.core.BetterConfigManager;
import com.reggarf.mods.world_first_join_message.api.JoinPlugin;
import com.reggarf.mods.world_first_join_message.configs.WFJMConfig;

public class CommonClass {


    public static void init() {
        JoinPlugin.register();
        Constants.CONFIG = BetterConfigManager.register(WFJMConfig.class);
    }
}