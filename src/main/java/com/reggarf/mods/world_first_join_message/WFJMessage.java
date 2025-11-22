package com.reggarf.mods.world_first_join_message;

import com.mojang.logging.LogUtils;
import com.reggarf.mods.better_lib.config.core.BetterConfigManager;
import com.reggarf.mods.better_lib.config.core.BetterConfigScreenFactory;
import com.reggarf.mods.better_lib.config.gui.ConfigScreenHandler;
import com.reggarf.mods.world_first_join_message.api.JoinPlugin;
import com.reggarf.mods.world_first_join_message.configs.WFJMConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

@Mod(WFJMessage.MOD_ID)
public class WFJMessage {
    public static final String MOD_ID = "world_first_join_message";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static WFJMConfig CONFIG;

    public WFJMessage(IEventBus modEventBus, ModContainer modContainer) {
        JoinPlugin.register();
        CONFIG = BetterConfigManager.register(WFJMConfig.class);
        modEventBus.addListener(this::onClientSetup);
    }
    private void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ConfigScreenHandler.register(MOD_ID, parent ->
                    BetterConfigScreenFactory.from(WFJMConfig.class, CONFIG, parent)
            );
        });
    }
}
