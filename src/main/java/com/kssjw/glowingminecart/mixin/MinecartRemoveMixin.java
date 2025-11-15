package com.kssjw.glowingminecart.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kssjw.glowingminecart.manager.LightSourceManager;

@Mixin(Entity.class)
public class MinecartRemoveMixin {

    @Inject(method = "remove", at = @At("HEAD"))
    private void onRemove(Entity.RemovalReason reason, CallbackInfo ci) {
        Entity self = (Entity)(Object)this;

        if (self instanceof AbstractMinecartEntity minecart) {
            LightSourceManager.removeLightSource(minecart);
        }
    }
}