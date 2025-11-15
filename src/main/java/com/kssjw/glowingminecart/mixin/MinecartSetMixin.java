package com.kssjw.glowingminecart.mixin;

import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kssjw.glowingminecart.manager.LightSourceManager;

@Mixin(AbstractMinecartEntity.class)
public class MinecartSetMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        LightSourceManager.updateLightSource((AbstractMinecartEntity)(Object)this);
    }
}