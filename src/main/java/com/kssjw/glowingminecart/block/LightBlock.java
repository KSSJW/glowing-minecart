package com.kssjw.glowingminecart.block;

import com.kssjw.glowingminecart.id.ModId;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class LightBlock {
    public static final Block LIGHT_BLOCK = Blocks.register(
        RegistryKey.of(
            RegistryKeys.BLOCK,
            Identifier.of(ModId.MODID, "light_block")
        ),
        new Block(AbstractBlock.Settings.create()
            .luminance(state -> 15) // 亮度
            .noCollision()  // 无碰撞
            .nonOpaque()    // 不阻挡光线
            .dropsNothing() // 无掉落物
            .strength(-1, 3600000.0F)   // 像笨猫猫的尾巴(?)一样硬
            .replaceable()  // 可被替换
            .air()  // 像空气一样
            .pistonBehavior(PistonBehavior.IGNORE)  // 忽略活塞交互
        ));

    public static void registerBlock() {
    }
}