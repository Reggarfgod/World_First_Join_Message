package com.reggarf.mods.world_first_join_message;

import com.reggarf.mods.better_lib.config.core.BetterConfigScreenFactory;
import com.reggarf.mods.better_lib.gui.screen.BetterConfigScreenHandler;
import com.reggarf.mods.world_first_join_message.configs.WFJMConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.mojang.text2speech.Narrator.LOGGER;

@Mod(WFJMessage.MOD_ID)
public class WFJMessage_forge {

    public WFJMessage_forge() {
        CommonClass.init();
    }
    @Mod.EventBusSubscriber(modid = WFJMessage.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> BetterConfigScreenHandler.register("better_lib",
                    parent -> BetterConfigScreenFactory.from(WFJMConfig.class, WFJMessage.CONFIG, parent)));
            LOGGER.info("Better_lib: Client setup complete, Minecraft user: {}", Minecraft.getInstance().getUser().getName());
        }
    }
}