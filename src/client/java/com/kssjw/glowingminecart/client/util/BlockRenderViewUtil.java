package com.kssjw.glowingminecart.client.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;

public class BlockRenderViewUtil {

    // 强制刷新一次光照更新,在实体移除后调用，避免残留亮度
    public static void forceLightUpdate() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world != null && client.worldRenderer != null) {
            WorldRenderer renderer = client.worldRenderer;
            
            // 延迟触发一次光照重建
            DelayUtil.schedule(5, () -> {
                renderer.reload();
                System.out.println("[GlowingMinecart] Force light update 强制刷新光照");
            });
        }
    }
}