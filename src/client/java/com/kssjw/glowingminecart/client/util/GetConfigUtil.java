package com.kssjw.glowingminecart.client.util;

import com.kssjw.glowingminecart.client.config.ValueConfig;

import me.shedaniel.autoconfig.AutoConfig;

public class GetConfigUtil {

    // 取配置实例
    public static ValueConfig config = AutoConfig.getConfigHolder(ValueConfig.class).getConfig();

    public static double getRadius() {
        return (double)config.intRadius;
    }

    public static int getLuminance() {
        return config.luminance;
    }
}
