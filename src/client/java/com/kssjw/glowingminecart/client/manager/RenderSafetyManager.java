package com.kssjw.glowingminecart.client.manager;

public class RenderSafetyManager {

    private static boolean enabled = true;

    public static void enable() { enabled = true; }
    public static void disable() { enabled = false; }
    public static boolean state() { return enabled; }
}