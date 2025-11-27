package com.kssjw.glowingminecart.client.manager;

import com.kssjw.glowingminecart.client.config.ValueConfig;
import com.kssjw.glowingminecart.client.util.LightUpdateUtil;

import me.shedaniel.autoconfig.AutoConfig;

public class ConfigManager {

    // 取配置实例
    public static ValueConfig config = AutoConfig.getConfigHolder(ValueConfig.class).getConfig();

    public static void reloadAction() {
        if (config.reloadAction == true) {
            LightUpdateUtil.reload();
            config.reloadAction = false;    // 按钮归位
        }
    }

    public static double getRadius() {
        return (double)config.intRadius;
    }

    public static int getLuminance() {
        return config.luminance;
    }
}
