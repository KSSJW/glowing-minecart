package com.kssjw.glowingminecart.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kssjw.glowingminecart.client.shared.SharedValue;
import com.kssjw.glowingminecart.util.DelayUtil;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.ClientLoginPacketListener;

@Mixin(ClientConnection.class)
public abstract class ConnectMixin {
    @Inject(
        method = "connect(Ljava/lang/String;ILnet/minecraft/network/listener/ClientLoginPacketListener;)V",
        at = @At("HEAD")
    )
    private void onLoginConnect(String address, int port, ClientLoginPacketListener listener, CallbackInfo ci) {
        DelayUtil.schedule(40, () -> {
            SharedValue.renderEnable = true;
            System.out.println("[GlowingMinecart] Starting 启用渲染注入");
        });
        
    }
}