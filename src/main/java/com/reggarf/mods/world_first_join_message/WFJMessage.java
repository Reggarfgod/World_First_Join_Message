package com.reggarf.mods.world_first_join_message;

import com.mojang.logging.LogUtils;
import com.reggarf.mods.world_first_join_message.configs.ModConfig;
import com.reggarf.mods.world_first_join_message.events.WFJMHandler;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(WFJMessage.MOD_ID)
public class WFJMessage {
    public static final String MOD_ID = "world_first_join_message";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static ModConfig CONFIG;

    public WFJMessage(IEventBus modEventBus, ModContainer modContainer) {
        init();
        NeoForge.EVENT_BUS.register(WFJMHandler.class);
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (container, parent) -> {
            return AutoConfig.getConfigScreen(ModConfig.class, parent).get();
        });

    }

    public static void init() {
        AutoConfig.register(ModConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    }

}
