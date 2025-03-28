package com.reggarf.mods.world_first_join_message;




import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class WFJMessageClient {

    public WFJMessageClient(IEventBus modBus) {
        //ArtifactsClient.init();

        modBus.addListener(this::onClientSetup);

    }

    public void onClientSetup(FMLClientSetupEvent event) {

    }

}
