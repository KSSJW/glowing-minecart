package com.kssjw.glowingminecart.client.menu;

import com.kssjw.glowingminecart.client.config.ValueConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.autoconfig.AutoConfigClient;

public class ConfigMenu implements ModMenuApi {
    
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfigClient.getConfigScreen(ValueConfig.class, parent).get();
    }
}
