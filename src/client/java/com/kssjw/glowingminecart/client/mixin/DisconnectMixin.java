package com.kssjw.glowingminecart.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kssjw.glowingminecart.client.shared.SharedValue;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.network.ClientConnection;
import net.minecraft.text.Text;

@Mixin(ClientConnection.class)
public class DisconnectMixin {
    @Inject(method = "disconnect", at = @At("HEAD"))
    private void onDisconnect(Text reason, CallbackInfo ci) {
        SharedValue.ENABLED = false;
        SharedValue.boosted = 0;
        System.out.println("[GlowingMinecart] Cleaning 关闭渲染注入，清理残留逻辑");
    }
}