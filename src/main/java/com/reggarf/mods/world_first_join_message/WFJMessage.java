package com.reggarf.mods.world_first_join_message;



import com.reggarf.mods.world_first_join_message.configs.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;

import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(WFJMessage.MOD_ID)
public class WFJMessage {
    public static final String MOD_ID = "world_first_join_message";
    public static final Logger LOGGER = LogManager.getLogger();
    public static ModConfig CONFIG;


    public WFJMessage() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        init();
        if (FMLEnvironment.dist == Dist.CLIENT) {
            new WFJMessageClient(modBus);
        }
        modBus.addListener(this::onCommonSetup);

        registerConfig();
    }
    private void registerConfig() {
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (client, parent) -> AutoConfig.getConfigScreen(ModConfig.class, parent).get()
                )
        );
    }

    public static void init() {
        AutoConfig.register(ModConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    }
    private void onCommonSetup(FMLCommonSetupEvent event) {

    }
}
