package com.kssjw.glowingminecart.client;

import com.kssjw.glowingminecart.client.util.DelayUtil;
import com.kssjw.glowingminecart.client.util.MinecartCacheUtil;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;

public class GlowingMinecart implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickCallback.EVENT.register(client -> {

            // 注册延时执行工具
            DelayUtil.tick();

            // 注册缓存工具
            if (client.world != null) MinecartCacheUtil.update(client.world);

            if (client.world != null) {
                for (Entity e : client.world.getEntities()) {

                    // 只刷新矿车附近的方块
                    if (e instanceof AbstractMinecartEntity) {
                        AbstractMinecartEntity m = (AbstractMinecartEntity) e;
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