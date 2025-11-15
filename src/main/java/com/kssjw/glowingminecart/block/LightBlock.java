package com.kssjw.glowingminecart.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;

public class LightBlock extends Block {
    public LightBlock() {
        super(AbstractBlock.Settings.create()
            .luminance(state -> 15) // 最大亮度
            .noCollision()
            .nonOpaque()
            .dropsNothing()
            .strength(-1, 3600000.0F)
            .replaceable()
            .air()
        );
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;   // 不渲染模型
    }
}