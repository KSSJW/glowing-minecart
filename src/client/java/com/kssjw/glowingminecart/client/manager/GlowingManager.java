package com.kssjw.glowingminecart.client.manager;

import java.util.ArrayList;
import java.util.List;

import com.kssjw.glowingminecart.client.util.MinecartCacheUtil;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;

public class GlowingManager {
    public static int boostedValue(LightType type, BlockPos pos) {
        
        final int LEGAL_LIGHT_MAX = 15; // 最大合法光照值
        final int ILLUMINATION_LEVEL_MAX = ConfigManager.getLuminance();
        final double RADIUS = ConfigManager.getRadius();

        // 安全开关拦截
        if (RenderSafetyManager.state() == false) return -1;

        // 照亮等级为 0 时不更改渲染
        if (ILLUMINATION_LEVEL_MAX == 0) return -1;

        ClientWorld world = MinecraftClient.getInstance().world;
        if (world == null) return -1;

        if (type != LightType.BLOCK) return -1;

        int original = world.getLightingProvider().get(type).getLightLevel(pos);
        int boosted = original;

        List<BlockPos> snapshot = new ArrayList<>(MinecartCacheUtil.getMinecarts());    // 快照副本
        for (BlockPos minecartPos : snapshot) {

            if (minecartPos == null) return -1;

            double dx = (pos.getX() + 0.5) - minecartPos.getX();
            double dy = (pos.getY() + 0.5) - minecartPos.getY();
            double dz = (pos.getZ() + 0.5) - minecartPos.getZ();
            double distSq = dx*  dx + dy * dy + dz * dz;

            if (distSq < RADIUS * RADIUS) {
                double dist = Math.sqrt(distSq);

                // 线性衰减光照
                int decayLight = (int)Math.max(0, ILLUMINATION_LEVEL_MAX - (dist / RADIUS) * ILLUMINATION_LEVEL_MAX);

                // 光照等级不超过合法范围
                decayLight = Math.min(decayLight, LEGAL_LIGHT_MAX);
                boosted = Math.min(boosted, LEGAL_LIGHT_MAX);

                // 提升方块光照，取较大值
                boosted = Math.max(boosted, decayLight);
            }
        }
        return boosted;
    }
}
