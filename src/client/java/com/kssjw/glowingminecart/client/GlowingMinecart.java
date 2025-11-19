package com.kssjw.glowingminecart.client;

import com.kssjw.glowingminecart.util.DelayUtil;
import com.kssjw.glowingminecart.util.MinecartCache;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;

public class GlowingMinecart implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            // 注册延时执行工具
            DelayUtil.tick();

            // 注册缓存工具
            if (client.world != null) MinecartCache.update(client.world);

            if (client.world != null) {
                for (Entity e : client.world.getEntities()) {

                    // 只刷新矿车附近的方块
                    if (e instanceof AbstractMinecartEntity m) {
                        BlockPos pos = m.getBlockPos();
                        
                        final int RADIUS = 15;   // 刷新矿车附近区域的半径

                        client.worldRenderer.scheduleBlockRenders(
                            pos.getX() - RADIUS, pos.getY() - RADIUS, pos.getZ() - RADIUS,
                            pos.getX() + RADIUS, pos.getY() + RADIUS, pos.getZ() + RADIUS
                        );
                    }
                }
            }
        });
    }
}