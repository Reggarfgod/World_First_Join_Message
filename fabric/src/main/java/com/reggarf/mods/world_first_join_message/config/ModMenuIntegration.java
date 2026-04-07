package com.reggarf.mods.world_first_join_message.config;




import com.reggarf.mods.better_lib.config.core.BetterConfigScreenFactory;
import com.reggarf.mods.world_first_join_message.Constants;
import com.reggarf.mods.world_first_join_message.configs.WFJMConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> BetterConfigScreenFactory.from(WFJMConfig.class, Constants.CONFIG, parent);
    }
}