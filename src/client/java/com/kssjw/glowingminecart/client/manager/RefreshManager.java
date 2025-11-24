package com.kssjw.glowingminecart.client.manager;

import com.kssjw.glowingminecart.client.util.GetConfigUtil;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;

public class RefreshManager {
    public static void refresh(MinecraftClient client) {
        for (Entity e : client.world.getEntities()) {

            // 只刷新矿车附近的方块
            if (e instanceof AbstractMinecartEntity m) {
                BlockPos pos = m.getBlockPos();
                
                final int RADIUS = (int)GetConfigUtil.getRadius() + 1;   // 刷新矿车附近区域的半径

                client.worldRenderer.scheduleBlockRenders(
                    pos.getX() - RADIUS, pos.getY() - RADIUS, pos.getZ() - RADIUS,
                    pos.getX() + RADIUS, pos.getY() + RADIUS, pos.getZ() + RADIUS
                );
            }
        }
    }
}
