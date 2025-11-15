package com.kssjw.glowingminecart.block;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlock {
    public static final Block INVISIBLE_LIGHT = Registry.register(
        Registries.BLOCK,
        Identifier.of("glowing-minecart", "light_block"),
        new LightBlock()
    );

    public static void registerBlocks() {
        // 在 Mod 初始化时调用
    }
}