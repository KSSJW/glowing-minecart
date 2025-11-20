package com.kssjw.glowingminecart.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kssjw.glowingminecart.client.manager.RenderSafetyManager;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.network.ClientConnection;
import net.minecraft.text.Text;

@Mixin(ClientConnection.class)
public abstract class DisconnectMixin {
    @Inject(method = "disconnect", at = @At("HEAD"))
    private void onDisconnect(Text reason, CallbackInfo ci) {
        RenderSafetyManager.disable();
        System.out.println("[GlowingMinecart] Closing 关闭渲染注入");
    }

    @Inject(method = "disconnect", at = @At("TAIL"))
    private void afterDisconnect(Text reason, CallbackInfo ci) {
        RenderSafetyManager.enable();
        System.out.println("[GlowingMinecart] Default State 还原渲染注入状态");
    }
}