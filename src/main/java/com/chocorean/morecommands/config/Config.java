package com.chocorean.morecommands.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config {
    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_DATABASE = "database";

    public static Configuration config;
    private static String file = "config/morecommands.cfg";

    public static void init() {
        config = new Configuration(new File(file));
        try {
            config.load();
        } catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        } finally {
            config.save();
        }
    }
}