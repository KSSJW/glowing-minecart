package com.kssjw.glowingminecart.client.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.LightType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kssjw.glowingminecart.client.manager.GlowingManager;

@Mixin(BlockRenderView.class)
public interface BlockRenderViewMixin {
    @Inject(method = "getLightLevel", at = @At("TAIL"), cancellable = true)
    private void gm$boostMinecartLight(LightType type, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        int boosted = GlowingManager.getBoosted(type, pos);
        if (boosted == -1) return;
        cir.setReturnValue(boosted);
    }
}