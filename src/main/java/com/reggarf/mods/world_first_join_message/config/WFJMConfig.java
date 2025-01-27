package com.reggarf.mods.world_first_join_message.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class WFJMConfig {
    private static final WFJMConfig INSTANCE = new WFJMConfig();

    private final WFJMClientConfig client;
    private final WFJMCommonConfig common;

    public WFJMConfig() {
        var client = new ForgeConfigSpec.Builder().configure(WFJMClientConfig::new);
        this.client = client.getLeft();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, client.getRight());

        var common = new ForgeConfigSpec.Builder().configure(WFJMCommonConfig::new);
        this.common = common.getLeft();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, common.getRight());
    }

    public static WFJMClientConfig getClient() {
        return INSTANCE.client;
    }

    public static WFJMCommonConfig getCommon() {
        return INSTANCE.common;
    }
}
