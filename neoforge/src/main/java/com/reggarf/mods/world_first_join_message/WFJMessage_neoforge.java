package com.reggarf.mods.world_first_join_message;


import com.reggarf.mods.better_lib.config.core.BetterConfigScreenFactory;
import com.reggarf.mods.better_lib.gui.screen.BetterConfigScreenHandler;
import com.reggarf.mods.world_first_join_message.configs.WFJMConfig;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import static com.mojang.text2speech.Narrator.LOGGER;

@Mod(Constants.MOD_ID)
public class WFJMessage_neoforge {

    public WFJMessage_neoforge(IEventBus eventBus) {
        CommonClass.init();
    }

    @EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> BetterConfigScreenHandler.register("better_lib",
                    parent -> BetterConfigScreenFactory.from(WFJMConfig.class, Constants.CONFIG, parent)));
            LOGGER.info("Better_lib: Client setup complete, Minecraft user: {}", Minecraft.getInstance().getUser().getName());
        }
    }
}