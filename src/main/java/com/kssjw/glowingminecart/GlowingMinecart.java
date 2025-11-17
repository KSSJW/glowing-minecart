package com.kssjw.glowingminecart;

import com.kssjw.glowingminecart.block.ModBlockRegister;
import com.kssjw.glowingminecart.manager.LightSourceManager;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class GlowingMinecart implements ModInitializer {
    @Override
    public void onInitialize() {

        // 注册方块
        ModBlockRegister.registerInit();

        // 注册每个世界 tick 回调
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            LightSourceManager.tick(world);
        });
    }
}