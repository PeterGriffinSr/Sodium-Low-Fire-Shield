package com.codezz.sodium_low_fire_shield.config;

public class SodiumLowFireShieldConfig {
    private static boolean lowFireEnabled = true;
    private static boolean lowShieldEnabled = true;

    public static boolean isLowFireEnabled() {
        return lowFireEnabled;
    }

    public static void setLowFireEnabled(boolean enabled) {
        lowFireEnabled = enabled;
    }

    public static boolean isLowShieldEnabled() {
        return lowShieldEnabled;
    }

    public static void setLowShieldEnabled(boolean enabled) {
        lowShieldEnabled = enabled;
    }

}
