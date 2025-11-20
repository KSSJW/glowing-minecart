package com.kssjw.glowingminecart.client.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;

public class MinecartCache {
    private static List<BlockPos> cachedMinecarts = new ArrayList<>();

    public static void update(ClientWorld world) {
        if (world == null) return;
        cachedMinecarts.clear();
        for (Entity e : world.getEntities()) {
            if (e instanceof AbstractMinecartEntity m) {
            cachedMinecarts.add(m.getBlockPos());
            }
        }
    }

    public static List<BlockPos> getMinecarts() {
        return cachedMinecarts;
    }
}