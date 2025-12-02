package com.kssjw.glowingminecart.client.manager;

import com.kssjw.glowingminecart.client.config.ValueConfig;
import com.kssjw.glowingminecart.client.util.LightUpdateUtil;

import me.shedaniel.autoconfig.AutoConfig;

public class ConfigManager {

    // 获取配置实例
    private static ValueConfig config = AutoConfig.getConfigHolder(ValueConfig.class).getConfig();

    public static void reloadRenderer() {
        if (config.shouldReloadRenderer == true) {
            LightUpdateUtil.reload();
            config.shouldReloadRenderer = false;    // 按钮归位
        }
    }  

    public static boolean isEnabledByUser() {
        return config.enabledByUser;
    }

    public static double getRadius() {
        return (double)config.intRadius;
    }

    public static int getLuminance() {
        return config.luminance;
    }

    /* ------ */

    public static boolean isApplyToMinecart() {
        return config.applyToMinecart;
    }

    public static boolean isApplyToChestMinecart() {
        return config.applyToChestMinecart;
    }

    public static boolean isApplyToFurnaceMinecart() {
        return config.applyToFurnaceMinecart;
    }

    public static boolean isApplyToTNTMinecart() {
        return config.applyToTNTMinecart;
    }

    public static boolean isApplyToHopperMinecart() {
        return config.applyToHopperMinecart;
    }

    public static boolean isApplyToSpawnerMinecart() {
        return config.applyToSpawnerMinecart;
    }

    public static boolean isApplyToCommandBlockMinecart() {
        return config.applyToCommandBlockMinecart;
    }

    /* ------ */

    public static boolean isEnableConstantLight() {
        return config.enableConstantLight;
    }

    public static double getConstantLightRadius() {
        return (double)config.intConstantLightRadius;
    }

    public static boolean isOnlyInMotion() {
        return config.onlyInMotion;
    }

    public static boolean isOnlyHasPasPassenger() {
        return config.onlyHasPassenger;
    }
}
