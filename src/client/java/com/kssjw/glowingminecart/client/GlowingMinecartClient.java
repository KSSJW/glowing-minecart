package com.kssjw.glowingminecart.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.util.math.BlockPos;

public class GlowingMinecartClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world != null && client.player != null) {
                BlockPos pos = client.player.getBlockPos();
                // 刷新玩家周围 8 格的渲染区域
                client.worldRenderer.scheduleBlockRenders(
                    pos.getX() - 8, pos.getY() - 8, pos.getZ() - 8,
                    pos.getX() + 8, pos.getY() + 8, pos.getZ() + 8
                );
            }
        });
    }
}