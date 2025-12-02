package com.kssjw.glowingminecart.client.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "glowing-minecart")
public class ValueConfig implements ConfigData {

    // 重载渲染器，其实是个Boolen开关的自动按钮（
    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.Tooltip(count = 6)
    public boolean shouldReloadRenderer = false;

    // 用户级启用状态开关
    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.Tooltip(count = 1)
    public boolean enabledByUser = true;
    
    // 亮度等级
    @ConfigEntry.Category("general")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 15)
    @ConfigEntry.Gui.Tooltip(count = 1)
    public int luminance = 14;
    
    // 被照亮的半径 （int类型）
    @ConfigEntry.Category("general")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 15)
    @ConfigEntry.Gui.Tooltip(count = 1)
    public int intRadius = 15;

    /* ------ */

    // 是否作用于矿车 20100618
    @ConfigEntry.Category("types")
    public boolean applyToMinecart = true;

    // 是否作用于运输矿车 v1.0.14
    @ConfigEntry.Category("types")
    public boolean applyToChestMinecart = true;

    // 是否作用于动力矿车 v1.0.14
    @ConfigEntry.Category("types")
    public boolean applyToFurnaceMinecart = true;

    // 是否作用于TNT矿车 13w02a
    @ConfigEntry.Category("types")
    public boolean applyToTNTMinecart = true;

    // 是否作用于漏斗矿车 13w03a
    @ConfigEntry.Category("types")
    public boolean applyToHopperMinecart = true;

    // 是否作用于刷怪笼矿车 13w06a
    @ConfigEntry.Category("types")
    public boolean applyToSpawnerMinecart = true;

    // 是否作用于命令方块矿车 13w39a
    @ConfigEntry.Category("types")
    public boolean applyToCommandBlockMinecart = true;
    
    /* ------ */

    // 是否启用发光不衰减
    @ConfigEntry.Category("advanced")
    @ConfigEntry.Gui.Tooltip(count = 1)
    public boolean enableConstantLight = false;

    // 发光不衰减半径
    @ConfigEntry.Category("advanced")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 15)
    public int intConstantLightRadius = 1;

    // 是否仅运动中的矿车发光
    @ConfigEntry.Category("advanced")
    @ConfigEntry.Gui.Tooltip(count = 2)
    public boolean onlyInMotion = false;

    // 是否仅有乘客的矿车发光
    @ConfigEntry.Category("advanced")
    @ConfigEntry.Gui.Tooltip(count = 3)
    public boolean onlyHasPassenger = false;
}