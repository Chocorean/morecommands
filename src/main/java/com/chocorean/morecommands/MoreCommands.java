package com.chocorean.morecommands;

import com.chocorean.morecommands.command.*;
import com.chocorean.morecommands.config.MoreCommandsConfig;
import com.chocorean.morecommands.misc.TpHandler;
import com.chocorean.morecommands.storage.datasource.DatabaseSourceStrategy;
import com.chocorean.morecommands.storage.datasource.FileDataSourceStrategy;
import com.chocorean.morecommands.storage.datasource.IDataSourceStrategy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;

@Mod(modid = MoreCommands.MODID, name = MoreCommands.NAME, version = MoreCommands.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class MoreCommands
{
    static final String MODID = "morecommands";
    static final String NAME = "More Commands";
    static final String VERSION = "1.6";

    public static final Logger LOGGER = FMLLog.log;
    private static MoreCommandsConfig config;
    private static IDataSourceStrategy strategy;

    public static TpHandler handler;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new MoreCommandsConfig(event.getSuggestedConfigurationFile());
        switch (config.getStorageStrategy().toUpperCase()) {
            case "DATABASE":
                MoreCommands.strategy = new DatabaseSourceStrategy(config);
                LOGGER.info("[AuthMod] Now using DatabaseAuthenticationStrategy.");
                break;
            case "FILE":
                MoreCommands.strategy = new FileDataSourceStrategy(Paths.get(
                        event.getModConfigurationDirectory().getAbsolutePath(),
                        MODID + ".csv").toFile());
                LOGGER.info("[Authmod] Now using FileAuthenticationStrategy.");
                break;
            default:
                MoreCommands.strategy = new FileDataSourceStrategy(Paths.get(
                        event.getModConfigurationDirectory().getAbsolutePath(),
                        MODID + ".csv").toFile());
                LOGGER.info("[AuthMod] Unknown authentication strategy selected. A .csv file is now used.");
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        handler = new TpHandler();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {}

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        LOGGER.info("Adding MoreCommands Event Handler");
        MinecraftForge.EVENT_BUS.register(new Handler());
        if (config.isSpawnEnabled()) {
            LOGGER.info("Adding /spawn");
            event.registerServerCommand(new SpawnCommand());
            LOGGER.info("Adding /setspawn");
            event.registerServerCommand(new SetSpawnCommand());
        }
        if (config.isHomeEnabled()) {
            LOGGER.info("Adding /home");
            event.registerServerCommand(new HomeCommand(strategy));
            LOGGER.info("Adding /sethome");
            event.registerServerCommand(new SetHomeCommand(strategy));
        }
        if (config.isBackEnabled()) {
            LOGGER.info("Adding /back");
            event.registerServerCommand(new BackCommand());
        }
        if (config.isTpaEnabled()) {
            LOGGER.info("Adding /tpa");
            event.registerServerCommand(new TpaCommand());
            LOGGER.info("Adding /tpahere");
            event.registerServerCommand(new TpaHereCommand());
        }
        if (config.isWarpEnabled()) {
            LOGGER.info("Adding /warps");
            event.registerServerCommand(new WarpsCommand(strategy));
            LOGGER.info("Adding /warp");
            event.registerServerCommand(new WarpCommand(strategy));
            LOGGER.info("Adding /setwarp");
            event.registerServerCommand(new SetWarpCommand(strategy));
        }
        if (config.isVanishEnabled()) {
            LOGGER.info("Adding /vanish");
            event.registerServerCommand(new VanishCommand());
        }
        if (config.isInvseeEnabled()) {
            LOGGER.info("Adding /invsee");
            event.registerServerCommand(new InvSeeCommand());
        }
    }

    public static MoreCommandsConfig getConfig() {
        return config;
    }

    public static IDataSourceStrategy getDataSourceStrategy() {
        return MoreCommands.strategy;
    }
}
