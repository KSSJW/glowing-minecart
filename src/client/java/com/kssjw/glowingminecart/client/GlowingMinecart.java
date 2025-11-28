package com.kssjw.glowingminecart.client;

import com.kssjw.glowingminecart.client.manager.HolderManager;
import com.kssjw.glowingminecart.client.manager.RefreshManager;
import com.kssjw.glowingminecart.client.util.DelayUtil;
import com.kssjw.glowingminecart.client.util.MinecartCacheUtil;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class GlowingMinecart implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        // 配置与监听器注册
        HolderManager.init();

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