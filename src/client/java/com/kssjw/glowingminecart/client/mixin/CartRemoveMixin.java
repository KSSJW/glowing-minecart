package com.kssjw.glowingminecart.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kssjw.glowingminecart.client.util.DelayUtil;
import com.kssjw.glowingminecart.client.util.LightUpdateUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;

@Mixin(Entity.class)
public abstract class CartRemoveMixin {
    @Inject(method = "remove", at = @At("TAIL"))
    private void onRemove(Entity.RemovalReason reason, CallbackInfo ci) {
        Entity self = (Entity)(Object)this;
        BlockPos pos = self.getBlockPos();
        if (self instanceof AbstractMinecartEntity) {
            DelayUtil.schedule(5, () -> LightUpdateUtil.update(pos.getX(), pos.getY(), pos.getZ(), pos.getX(), pos.getY(), pos.getZ()));    // 矿车被移除后延时刷新渲染
        }
    }
}
