package com.kssjw.glowingminecart.client.manager;

import java.util.ArrayList;
import java.util.List;

import com.kssjw.glowingminecart.client.util.CartCacheUtil;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;

public class GlowingManager {
    public static int getBoosted(LightType type, BlockPos pos) {
        
        final int LEGAL_LIGHT_MAX = 15; // 最大合法光照值
        final int ILLUMINATION_LEVEL_MAX = ConfigManager.getLuminance();
        final double RADIUS = ConfigManager.getRadius();

        // 安全开关拦截
        if (RenderSafetyManager.state() == false) return -1;

        // 用户级启用状态检查
        if (ConfigManager.isEnabledByUser() == false) return -1;

        ClientWorld world = MinecraftClient.getInstance().world;
        if (world == null) return -1;

        if (type != LightType.BLOCK) return -1;

        int original = world.getLightingProvider().get(type).getLightLevel(pos);
        int decayLight;
        int boosted = original;

        List<BlockPos> snapshot = new ArrayList<>(CartCacheUtil.getMinecarts());    // 快照副本
        for (BlockPos minecartPos : snapshot) {

            if (minecartPos == null) continue;

            double dx = (pos.getX() + 0.5) - minecartPos.getX();
            double dy = (pos.getY() + 0.5) - minecartPos.getY();
            double dz = (pos.getZ() + 0.5) - minecartPos.getZ();
            double distSq = dx*  dx + dy * dy + dz * dz;

            if (distSq < RADIUS * RADIUS) {
                double dist = Math.sqrt(distSq);

                if (ConfigManager.isEnableConstantLight() == true) {

                    // 若启用发光不衰减
                    double radiusCL = ConfigManager.getConstantLightRadius();
                    if (radiusCL > RADIUS) radiusCL = RADIUS; // 避免与渲染半径冲突
                    if (dist <= radiusCL) return boosted = ILLUMINATION_LEVEL_MAX; // 不衰减区域
                    double factor = Math.max(0.0, 1.0 - (dist * dist) / (RADIUS * RADIUS));
                    decayLight = (int)(ILLUMINATION_LEVEL_MAX * factor);

                } else {

                // 默认：线性衰减光照
                decayLight = (int)Math.max(0, ILLUMINATION_LEVEL_MAX - (dist / RADIUS) * ILLUMINATION_LEVEL_MAX);
                }

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
