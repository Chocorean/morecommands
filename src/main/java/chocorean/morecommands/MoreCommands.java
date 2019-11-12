package chocorean.morecommands;

import chocorean.morecommands.command.*;
import chocorean.morecommands.config.MoreCommandsConfig;
import chocorean.morecommands.misc.TpHandler;
import chocorean.morecommands.storage.datasource.DatabaseSourceStrategy;
import chocorean.morecommands.storage.datasource.FileDataSourceStrategy;
import chocorean.morecommands.storage.datasource.IDataSourceStrategy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Mod(modid = MoreCommands.MODID, name = MoreCommands.NAME, version = MoreCommands.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class MoreCommands
{
    public static final String MODID = "morecommands";
    static final String NAME = "MoreCommands";
    static final String VERSION = "1.10";

    private static IDataSourceStrategy strategy;
    public static TpHandler handler;

    public static final Logger LOGGER = FMLLog.log;
    public static Map<String, String> localization;

    private void fillLocalization() {
        localization = new HashMap<>();
        String path = String.format("assets/morecommands/lang/%s.lang", MoreCommandsConfig.language);
        LOGGER.info(String.format("[MoreCommands] Trying to load %s", path));
        InputStream inStream = getClass().getClassLoader().getResourceAsStream(path);
        Properties prop = new Properties();
        try {
            prop.load(inStream);
            LOGGER.info(String.format("[MoreCommands] Loaded %s", path));
        } catch (IOException e) {
            LOGGER.error("[MoreCommands] IOException in MoreCommands.java#fillLocalization:\n" + e.getMessage());
        } catch (NullPointerException e) {
            LOGGER.error(String.format("[MoreCommands] File %s does not exist!", path));
        }
        for (String s: prop.stringPropertyNames()) {
            localization.put(s, prop.getProperty(s));
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        fillLocalization();

        // Storage strategy
        switch (MoreCommandsConfig.DatabaseCategory.storageStrategy.toUpperCase()) {
            case "DATABASE":
                MoreCommands.strategy = new DatabaseSourceStrategy();
                LOGGER.info("[MoreCommands] Now using DatabaseAuthenticationStrategy.");
                break;
            case "FILE":
                MoreCommands.strategy = new FileDataSourceStrategy(Paths.get(
                        event.getModConfigurationDirectory().getAbsolutePath(),
                        MODID + ".csv").toFile());
                LOGGER.info("[MoreCommands] Now using FileAuthenticationStrategy.");
                break;
            default:
                MoreCommands.strategy = new FileDataSourceStrategy(Paths.get(
                        event.getModConfigurationDirectory().getAbsolutePath(),
                        MODID + ".csv").toFile());
                LOGGER.info("[MoreCommands] Unknown authentication strategy selected. A .csv file is now used.");
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
        if (MoreCommandsConfig.CommandCategory.isBackEnabled) {
            LOGGER.info("Adding /back");
            event.registerServerCommand(new BackCommand());
        }
        if (MoreCommandsConfig.CommandCategory.isEnderchestEnabled) {
            LOGGER.info("Adding /enderchest");
            event.registerServerCommand(new EnderchestCommand());
        }
        if (MoreCommandsConfig.CommandCategory.isHomeEnabled) {
            LOGGER.info("Adding /home");
            event.registerServerCommand(new HomeCommand(strategy));
            LOGGER.info("Adding /sethome");
            event.registerServerCommand(new SetHomeCommand(strategy));
        }
        if (MoreCommandsConfig.CommandCategory.isInvseeEnabled) {
            LOGGER.info("Adding /invsee");
            event.registerServerCommand(new InvseeCommand());
        }
        if (MoreCommandsConfig.CommandCategory.isKillallEnabled) {
            LOGGER.info("Adding /killall");
            event.registerServerCommand(new KillAllCommand());
        }
        if (MoreCommandsConfig.CommandCategory.isSpawnEnabled) {
            LOGGER.info("Adding /spawn");
            event.registerServerCommand(new SpawnCommand());
            LOGGER.info("Adding /setspawn");
            event.registerServerCommand(new SetSpawnCommand());
        }
        if (MoreCommandsConfig.CommandCategory.isTpaEnabled) {
            LOGGER.info("Adding /tpa");
            event.registerServerCommand(new TpaCommand());
            LOGGER.info("Adding /tpahere");
            event.registerServerCommand(new TpaHereCommand());
        }
        if (MoreCommandsConfig.CommandCategory.isVanishEnabled) {
            LOGGER.info("Adding /vanish");
            event.registerServerCommand(new VanishCommand());
        }
        if (MoreCommandsConfig.CommandCategory.isWarpEnabled) {
            LOGGER.info("Adding /warps");
            event.registerServerCommand(new WarpsCommand(strategy));
            LOGGER.info("Adding /warp");
            event.registerServerCommand(new WarpCommand(strategy));
            LOGGER.info("Adding /setwarp");
            event.registerServerCommand(new SetWarpCommand(strategy));
            LOGGER.info("Adding /delwarp");
            event.registerServerCommand(new DelWarpCommand(strategy));
        }
    }
}
