package com.kssjw.glowingminecart.client.manager;

import com.kssjw.glowingminecart.client.config.ValueConfig;
import com.kssjw.glowingminecart.client.util.LightUpdateUtil;
import com.kssjw.glowingminecart.client.util.LogUtil;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.util.ActionResult;

public class HolderManager {
    public static void init() {

        // 配置与监听器初始化
        ConfigHolder<ValueConfig> holder = AutoConfig.register(ValueConfig.class, GsonConfigSerializer::new);

        // 监听器，保存配置后触发
        holder.registerSaveListener((configHolder, config) -> {
            ConfigManager.reloadAction();
            LightUpdateUtil.reload();
            LogUtil.print("The configuration has been saved.");
            return ActionResult.SUCCESS;
        });

        // 监听器，加载配置后触发
        holder.registerLoadListener((configHolder, config) -> {
            ConfigManager.reloadAction();
            LightUpdateUtil.reload();
            LogUtil.print("Configuration has been loaded.");
            return ActionResult.SUCCESS;
        });
    }
}
