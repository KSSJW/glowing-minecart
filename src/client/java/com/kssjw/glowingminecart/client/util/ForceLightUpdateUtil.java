package com.kssjw.glowingminecart.client.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;

public class ForceLightUpdateUtil {

    // 强制刷新一次光照更新,在实体移除后调用，避免残留亮度
    public static void update(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world != null && client.worldRenderer != null) {
            WorldRenderer renderer = client.worldRenderer;
            final int RADIUS = 15;
            renderer.scheduleBlockRenders(minX - RADIUS, minY - RADIUS, minZ - RADIUS, maxX + RADIUS, maxY + RADIUS, maxZ + RADIUS);
            System.out.println("[GlowingMinecart] Force light update 强制刷新光照");
        }
    }
}