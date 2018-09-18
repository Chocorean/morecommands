package com.chocorean.morecommands;

import com.chocorean.morecommands.command.*;
import com.chocorean.morecommands.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.apache.logging.log4j.Logger;

@Mod(modid = MoreCommands.MODID, name = MoreCommands.NAME, version = MoreCommands.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class MoreCommands
{
    public static final String MODID = "morecommands";
    public static final String NAME = "More Commands";
    public static final String VERSION = "1.4";
    public static final String COMMON_PROXY = "com.chocorean.morecommands.proxy.CommonProxy";
    public static final String CLIENT_PROXY = "com.chocorean.morecommands.proxy.ClientProxy";

    @SidedProxy(clientSide = MoreCommands.CLIENT_PROXY, serverSide = MoreCommands.COMMON_PROXY)
    public static CommonProxy proxy;

    public static final Logger LOGGER = FMLLog.getLogger();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        File dir = new File("config/MoreCommands");
        if (!dir.exists())
            if (!dir.mkdir()) {
                LOGGER.error("Cannot create config folder. Things gonna be great.");
            }
        if (!new File("config/MoreCommands/home").exists()) {
            PrintWriter writer;
            try {
                writer = new PrintWriter("config/MoreCommands/home");
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!new File("config/MoreCommands/warp").exists()) {
            PrintWriter writer;
            try {
                writer = new PrintWriter("config/MoreCommands/warp");
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {}

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {}

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        LOGGER.info("Adding MoreCommands Event Handler");
        MinecraftForge.EVENT_BUS.register(new Handler());
        LOGGER.info("Adding /spawn");
        event.registerServerCommand(new SpawnCommand());
        LOGGER.info("Adding /setspawn");
        event.registerServerCommand(new SetSpawnCommand());
        LOGGER.info("Adding /home");
        event.registerServerCommand(new HomeCommand());
        LOGGER.info("Adding /sethome");
        event.registerServerCommand(new SetHomeCommand());
        LOGGER.info("Adding /back");
        event.registerServerCommand(new BackCommand());
        LOGGER.info("Adding /tpa");
        event.registerServerCommand(new TpaCommand());
        LOGGER.info("Adding /tpahere");
        event.registerServerCommand(new TpaHereCommand());
        LOGGER.info("Adding /warps");
        event.registerServerCommand(new WarpsCommand());
        LOGGER.info("Adding /warp");
        event.registerServerCommand(new WarpCommand());
        LOGGER.info("Adding /setwarp");
        event.registerServerCommand(new SetWarpCommand());
        LOGGER.info("Adding /vanish");
        event.registerServerCommand(new VanishCommand());
        LOGGER.info("Adding /invsee");
        event.registerServerCommand(new InvSeeCommand());
    }
}
