package com.kssjw.glowingminecart.client.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "glowing-minecart")
public class ValueConfig implements ConfigData {

    // 重载渲染器，其实是个Boolen开关的自动按钮（
    @ConfigEntry.Gui.Tooltip(count = 6)
    public boolean reloadAction = false;
    
    // 亮度等级
    @ConfigEntry.BoundedDiscrete(min = 0, max = 15)
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int luminance = 14;
    
    // 被照亮的半径 （int类型）
    @ConfigEntry.BoundedDiscrete(min = 1, max = 15) // 数值范围
    @ConfigEntry.Gui.Tooltip(count = 1) // 显示文字行数
    public int intRadius = 15;
}
