package com.kssjw.glowingminecart.client.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "glowing-minecart")
public class ValueConfig implements ConfigData {
    
    // 被照亮的半径 （int类型）
    @ConfigEntry.BoundedDiscrete(min = 0, max = 15) // 数值范围
    @ConfigEntry.Gui.Tooltip(count = 1) // 显示文字行数
    public int intRadius = 15;

    // 亮度等级
    @ConfigEntry.BoundedDiscrete(min = 0, max = 15)
    @ConfigEntry.Gui.Tooltip(count = 1)
    public int luminance = 14;
}
