package com.kssjw.glowingminecart.client;

import com.kssjw.glowingminecart.client.config.ValueConfig;
import com.kssjw.glowingminecart.client.manager.ConfigManager;
import com.kssjw.glowingminecart.client.manager.RefreshManager;
import com.kssjw.glowingminecart.client.util.DelayUtil;
import com.kssjw.glowingminecart.client.util.LightUpdateUtil;
import com.kssjw.glowingminecart.client.util.MinecartCacheUtil;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.util.ActionResult;

public class GlowingMinecart implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        // 配置与监听器初始化
        ConfigHolder<ValueConfig> holder = AutoConfig.register(ValueConfig.class, GsonConfigSerializer::new);

        // 监听器，保存配置后触发
        holder.registerSaveListener((configHolder, config) -> {
            ConfigManager.reloadAction();
            LightUpdateUtil.reload();
            return ActionResult.SUCCESS;
        });

        // 监听器，加载配置后触发
        holder.registerLoadListener((configHolder, config) -> {
            ConfigManager.reloadAction();
            LightUpdateUtil.reload();
            return ActionResult.SUCCESS;
        });

        // Tick事件注册
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            // 注册延时执行工具
            DelayUtil.tick();

            // 注册缓存工具
            if (client.world != null) MinecartCacheUtil.update(client.world);

            // 注册刷新工具
            if (client.world != null) RefreshManager.refresh(client);
        });
    }
}