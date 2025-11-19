package com.kssjw.glowingminecart.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kssjw.glowingminecart.util.BlockRenderViewUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;

@Mixin(Entity.class)
public abstract class MinecartRemoveMixin {
    @Inject(method = "remove", at = @At("TAIL"))
    private void onRemove(Entity.RemovalReason reason, CallbackInfo ci) {
        Entity self = (Entity)(Object)this;
        if (self instanceof AbstractMinecartEntity) {
            BlockRenderViewUtil.forceLightUpdate(); // 矿车被移除后强制刷新渲染
        }
    }
}
