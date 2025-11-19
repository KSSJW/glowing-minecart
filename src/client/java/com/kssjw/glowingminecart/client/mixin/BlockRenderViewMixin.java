package com.kssjw.glowingminecart.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.LightType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kssjw.glowingminecart.client.shared.SharedValue;

@Mixin(BlockRenderView.class)
public interface BlockRenderViewMixin {
    @Inject(method = "getLightLevel", at = @At("HEAD"), cancellable = true)
    private void gm$boostMinecartLight(LightType type, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        
        // 安全开关拦截
        if (SharedValue.ENABLED == false) return;

        if (type != LightType.BLOCK) return;

        ClientWorld world = MinecraftClient.getInstance().world;
        if (world == null) return;

        int original = world.getLightingProvider().get(type).getLightLevel(pos);
        SharedValue.boosted = original;

        for (Entity e : world.getEntities()) {
            if (e instanceof AbstractMinecartEntity m) {
                double dx = (pos.getX() + 0.5) - m.getX();
                double dy = (pos.getY() + 0.5) - m.getY();
                double dz = (pos.getZ() + 0.5) - m.getZ();
                double distSq = dx*  dx + dy * dy + dz * dz;

                final int LEGAL_LIGHT_MAX = 14; // 最大合法光照值
                final double RADIUS = 15.0;   // 被照亮的半径
                final int ILLUMINATION_LEVEL_MAX = 14;  // 最大光照等级

                if (distSq < RADIUS * RADIUS) {
                    double dist = Math.sqrt(distSq);

                    // 线性衰减光照
                    int decayLight = (int)Math.max(0, ILLUMINATION_LEVEL_MAX - (dist / RADIUS) * ILLUMINATION_LEVEL_MAX);

                    // 光照等级不超过合法范围
                    decayLight = Math.min(decayLight, LEGAL_LIGHT_MAX);

                    // 提升方块光照
                    SharedValue.boosted = Math.max(original, decayLight);
                    SharedValue.boosted = Math.min(SharedValue.boosted, LEGAL_LIGHT_MAX);    // 再次确认，在初次进入游戏可能因为光照等级数值过大导致崩溃

                    break;
                }
            }
        }
        cir.setReturnValue(SharedValue.boosted);
    }
}