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

@Mixin(BlockRenderView.class)
public interface BlockRenderViewMixin {
    @Inject(method = "getLightLevel", at = @At("HEAD"), cancellable = true)
    private void gm$boostMinecartLight(LightType type, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (type != LightType.BLOCK) return;

        ClientWorld world = MinecraftClient.getInstance().world;
        if (world == null) return;

        int original = world.getLightingProvider().get(type).getLightLevel(pos);
        int boosted = original;

        for (Entity e : world.getEntities()) {
            if (e instanceof AbstractMinecartEntity m) {
                double dx = (pos.getX() + 0.5) - m.getX();
                double dy = (pos.getY() + 0.5) - m.getY();
                double dz = (pos.getZ() + 0.5) - m.getZ();
                double distSq = dx*dx + dy*dy + dz*dz;

                double radius = 6.0;
                if (distSq < radius*radius) {
                    double dist = Math.sqrt(distSq);
                    int decayLight = (int)Math.max(0, 14 - (dist / radius) * 14);
                    boosted = Math.max(original, decayLight);
                    break;
                }
            }
        }
        cir.setReturnValue(boosted);
    }
}