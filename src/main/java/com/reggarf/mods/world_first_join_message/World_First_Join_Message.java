package com.reggarf.mods.world_first_join_message;

import com.mojang.logging.LogUtils;
import com.reggarf.mods.world_first_join_message.config.WFJMConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(World_First_Join_Message.MOD_ID)
public class World_First_Join_Message {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "world_first_join_message";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public World_First_Join_Message() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);


        WFJMConfig.getCommon();

    }
}
