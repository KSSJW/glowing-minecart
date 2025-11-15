package com.kssjw.glowingminecart.manager;

import com.kssjw.glowingminecart.block.ModBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LightSourceManager {
    private static final Map<AbstractMinecartEntity, BlockPos> ACTIVE_LIGHTS = new HashMap<>();

    public static void tick(ServerWorld world) {
        Iterator<Map.Entry<AbstractMinecartEntity, BlockPos>> it = ACTIVE_LIGHTS.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<AbstractMinecartEntity, BlockPos> entry = it.next();
            AbstractMinecartEntity cart = entry.getKey();
            BlockPos oldPos = entry.getValue();

            // 计算矿车上方放置光源的新位置
            BlockPos newPos = cart.getBlockPos().up();

            // 移除旧光源（如果位置变化或者矿车死亡）
            if (!newPos.equals(oldPos) || !cart.isAlive()) {
                if (world.getBlockState(oldPos).isOf(ModBlock.INVISIBLE_LIGHT)) {
                    world.removeBlock(oldPos, false);
                }
                it.remove();
            }

            // 如果矿车还活着，放置新的光源并更新 ACTIVE_LIGHTS
            if (cart.isAlive()) {
                if (world.getBlockState(newPos).isAir()) {
                    world.setBlockState(newPos, ModBlock.INVISIBLE_LIGHT.getDefaultState());
                }
                ACTIVE_LIGHTS.put(cart, newPos);
            }
        }
    }

    // 每 tick 更新光源位置
    public static void updateLightSource(AbstractMinecartEntity cart) {
        World world = cart.getWorld();
        if (world.isClient()) return;

        BlockPos newPos = cart.getBlockPos().up();
        BlockPos oldPos = ACTIVE_LIGHTS.get(cart);

        if (newPos.equals(oldPos)) return;

        ServerWorld serverWorld = (ServerWorld) world;

        // 移除旧光源
        if (oldPos != null && serverWorld.getBlockState(oldPos).isOf(ModBlock.INVISIBLE_LIGHT)) {
            serverWorld.removeBlock(oldPos, false);
        }

        // 放置新光源
        if (serverWorld.getBlockState(newPos).isAir()) {
            serverWorld.setBlockState(newPos, ModBlock.INVISIBLE_LIGHT.getDefaultState());
            ACTIVE_LIGHTS.put(cart, newPos);
        }
    }

    // 双重保障，矿车被移除时清理光源
    public static void removeLightSource(AbstractMinecartEntity cart) {
        BlockPos pos = ACTIVE_LIGHTS.remove(cart);
        if (pos == null) return;

        World world = cart.getWorld();
        if (world.isClient()) return;

        if (world.getBlockState(pos).isOf(ModBlock.INVISIBLE_LIGHT)) {
            world.removeBlock(pos, false);
        }
    }
}