package com.reggarf.mods.world_first_join_message;

import com.mojang.logging.LogUtils;
import com.reggarf.mods.better_lib.config.core.BetterConfigManager;
import com.reggarf.mods.better_lib.config.core.BetterConfigScreenFactory;
import com.reggarf.mods.better_lib.config.gui.betterConfigScreenHandler;
import com.reggarf.mods.world_first_join_message.api.JoinPlugin;
import com.reggarf.mods.world_first_join_message.configs.WFJMConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(WFJMessage.MOD_ID)
public class WFJMessage {

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "world_first_join_message";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static WFJMConfig CONFIG;

    public WFJMessage(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();
        CONFIG = BetterConfigManager.register(WFJMConfig.class);
        JoinPlugin.register();
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> betterConfigScreenHandler.register("better_lib",
                    parent -> BetterConfigScreenFactory.from(WFJMConfig.class, CONFIG, parent)));
            LOGGER.info("Better_lib: Client setup complete, Minecraft user: {}", Minecraft.getInstance().getUser().getName());
        }
    }

}
