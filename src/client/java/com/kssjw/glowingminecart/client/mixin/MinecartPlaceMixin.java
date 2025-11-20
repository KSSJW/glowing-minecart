package com.kssjw.glowingminecart.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kssjw.glowingminecart.client.util.DelayUtil;
import com.kssjw.glowingminecart.client.util.ForceLightUpdateUtil;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;

@Mixin(ClientWorld.class)
public abstract class MinecartPlaceMixin {
    @Inject(method = "addEntity", at = @At("TAIL"))
    private void gm$onEntityAdded(Entity entity, CallbackInfo ci) {
        if (entity instanceof AbstractMinecartEntity) {
            BlockPos pos = entity.getBlockPos();
            DelayUtil.schedule(5, () -> ForceLightUpdateUtil.update(pos.getX(), pos.getY(), pos.getZ(), pos.getX(), pos.getY(), pos.getZ()));   // 矿车放置后延时刷新渲染
        }
    }
}
