package com.kssjw.glowingminecart.client.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;

public class CartCacheUtil {
    private static volatile List<BlockPos> cachedMinecarts = Collections.emptyList();

    public static void update(ClientWorld world) {
        List<BlockPos> newList = new ArrayList<>();
        if (world == null) return;
        cachedMinecarts.clear();
        for (Entity entity : world.getEntities()) {
            if (entity instanceof AbstractMinecartEntity) {

                // 筛选排除
                if (CartFilterUtil.isCartExcluded(entity) == true) continue;

                newList.add(entity.getBlockPos());
            }
        }
        cachedMinecarts = newList;
    }

    public static List<BlockPos> getMinecarts() {
        return cachedMinecarts;
    }
}