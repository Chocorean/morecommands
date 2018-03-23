package com.chocorean.morecommands;

import com.chocorean.morecommands.command.*;
import com.chocorean.morecommands.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@Mod(modid = MoreCommands.MODID, name = MoreCommands.NAME, version = MoreCommands.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class MoreCommands
{
    public static final String MODID = "morecommands";
    public static final String NAME = "More Commands";
    public static final String VERSION = "1.2";
    public static final String COMMON_PROXY = "com.chocorean.morecommands.proxy.CommonProxy";
    public static final String CLIENT_PROXY = "com.chocorean.morecommands.proxy.ClientProxy";

    @SidedProxy(clientSide = MoreCommands.CLIENT_PROXY, serverSide = MoreCommands.COMMON_PROXY)
    public static CommonProxy proxy;

    public static final Logger LOGGER = Logger.getLogger(MoreCommands.MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        File dir = new File("mods/MoreCommands");
        if (!dir.exists())
            if (!dir.mkdir()) {
                LOGGER.warning("Cannot create config folder. Things gonna be great.");
            }
        if (!new File("mods/MoreCommands/home").exists()) {
            PrintWriter writer;
            try {
                writer = new PrintWriter("mods/MoreCommands/home");
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!new File("mods/MoreCommands/warp").exists()) {
            PrintWriter writer;
            try {
                writer = new PrintWriter("mods/MoreCommands/warp");
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
        LOGGER.info("Registering MoreCommands Event Handler");
        MinecraftForge.EVENT_BUS.register(new Handler());
        LOGGER.info("Registering MoreCommands /spawn Command");
        event.registerServerCommand(new SpawnCommand());
        LOGGER.info("Registering AuthMod /setspawn Command");
        event.registerServerCommand(new SetSpawnCommand());
        LOGGER.info("Registering MoreCommands /home Command");
        event.registerServerCommand(new HomeCommand());
        LOGGER.info("Registering AuthMod /sethome Command");
        event.registerServerCommand(new SetHomeCommand());
        LOGGER.info("Registering MoreCommands /back Command");
        event.registerServerCommand(new BackCommand());
        LOGGER.info("Registering AuthMod /afk Command");
        event.registerServerCommand(new AFKCommand());
        LOGGER.info("Registering MoreCommands /tpa Command");
        event.registerServerCommand(new TpaCommand());
        LOGGER.info("Registering AuthMod /tpahere Command");
        event.registerServerCommand(new TpaHereCommand());
        LOGGER.info("Registering AuthMod /warps Command");
        event.registerServerCommand(new WarpsCommand());
        LOGGER.info("Registering AuthMod /warp Command");
        event.registerServerCommand(new WarpCommand());
        LOGGER.info("Registering AuthMod /setwarp Command");
        event.registerServerCommand(new SetwarpCommand());
    }
}
