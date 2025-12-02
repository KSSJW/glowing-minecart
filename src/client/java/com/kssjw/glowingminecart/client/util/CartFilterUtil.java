package com.kssjw.glowingminecart.client.util;

import com.kssjw.glowingminecart.client.manager.ConfigManager;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public class CartFilterUtil {
    public static boolean isCartExcluded(Entity entity) {
        if (
            ConfigManager.isOnlyInMotion() == true && entity.getVelocity().lengthSquared() < 1.0E-6    // 若仅运动中的矿车发光
            || ConfigManager.isOnlyHasPasPassenger() == true && !entity.hasPassengers()   // 若仅有乘客的矿车发光
            || ConfigManager.isApplyToMinecart() == false && (entity.getType() == EntityType.MINECART)    // 若不作用于矿车
            || ConfigManager.isApplyToChestMinecart() == false && (entity.getType() == EntityType.CHEST_MINECART)  // 若不作用于运输矿车
            || ConfigManager.isApplyToFurnaceMinecart() == false && (entity.getType() == EntityType.FURNACE_MINECART)  // 若不作用于动力矿车
            || ConfigManager.isApplyToTNTMinecart() == false && (entity.getType() == EntityType.TNT_MINECART)  // 若不作用于TNT矿车
            || ConfigManager.isApplyToHopperMinecart() == false && (entity.getType() == EntityType.HOPPER_MINECART)    // 若不作用于漏斗矿车
            || ConfigManager.isApplyToSpawnerMinecart() == false && (entity.getType() == EntityType.SPAWNER_MINECART)  // 若不作用于刷怪笼矿车
            || ConfigManager.isApplyToCommandBlockMinecart() == false && (entity.getType() == EntityType.COMMAND_BLOCK_MINECART)   // 若不作用于命令方块矿车
        ) {
            return true;
        }
        else return false;
    }
}