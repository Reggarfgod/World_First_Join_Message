package com.reggarf.mods.world_first_join_message;

import com.mojang.logging.LogUtils;
import com.reggarf.mods.world_first_join_message.configs.WFJMConfig;
import com.reggarf.mods.world_first_join_message.events.WFJMHandler;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;

public class WFJMessage implements ModInitializer {
    public static final String MOD_ID = "world_first_join_message";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static WFJMConfig CONFIG;
    @Override
    public void onInitialize() {
        WFJMHandler.register();
        init();
    }

        public static void init() {
            AutoConfig.register(WFJMConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
            CONFIG = AutoConfig.getConfigHolder(WFJMConfig.class).getConfig();

        }

}
