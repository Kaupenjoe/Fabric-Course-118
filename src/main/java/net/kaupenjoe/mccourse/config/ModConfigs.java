package net.kaupenjoe.mccourse.config;

import com.mojang.datafixers.util.Pair;
import net.kaupenjoe.mccourse.MCCourseMod;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static String TEST;
    public static int SOME_INT;
    public static double SOME_DOUBLE;
    public static boolean REPLACE_TITLESCREEN;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(MCCourseMod.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("key.test.value1", "Just a Testing string!"), "String");
        configs.addKeyValuePair(new Pair<>("key.test.value2", 50), "int");
        configs.addKeyValuePair(new Pair<>("key.test.value3", 4142.5), "double");
        configs.addKeyValuePair(new Pair<>("replace.titlescreen", true), "boolean");
    }

    private static void assignConfigs() {
        TEST = CONFIG.getOrDefault("key.test.value1", "Nothing");
        SOME_INT = CONFIG.getOrDefault("key.test.value2", 42);
        SOME_DOUBLE = CONFIG.getOrDefault("key.test.value3", 42.0d);
        REPLACE_TITLESCREEN = CONFIG.getOrDefault("replace.titlescreen", true);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
