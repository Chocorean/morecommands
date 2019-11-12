package chocorean.morecommands.config;

import chocorean.morecommands.MoreCommands;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = MoreCommands.MODID)
@Config.LangKey("morecommands.config.title")
public class MoreCommandsConfig {
    /* Localization */
    @Config.Comment("Localization")
    public static String language = "en_US";

    /* Commands registration */
    @Config(modid = MoreCommands.MODID, category = "Command")
    public static class CommandCategory {
        @Config.Comment("Defines if /back is registered.")
        public static boolean isBackEnabled = false;

        @Config.Comment("Defines if /enderchest is registered.")
        public static boolean isEnderchestEnabled = true;

        @Config.Comment("Defines if /home is registered.")
        public static boolean isHomeEnabled = true;

        @Config.Comment("Defines if /killall is registered.")
        public static boolean isKillallEnabled = true;

        @Config.Comment("Defines if /invsee is registered.")
        public static boolean isInvseeEnabled = true;

        @Config.Comment("Defines if /spawn is registered.")
        public static boolean isSpawnEnabled = true;

        @Config.Comment("Defines if /tpa* commands are registered.")
        public static boolean isTpaEnabled = true;

        @Config.Comment("Defines if /vanish is registered.")
        public static boolean isVanishEnabled = true;

        @Config.Comment("Defines if /warp* commands are registered.")
        public static boolean isWarpEnabled = true;
    }

    /* Database related config */
    @Config(modid = MoreCommands.MODID, category = "Database")
    public static class DatabaseCategory {
        @Config.Comment("Defines storage strategy (FILE or DATABASE)")
        public static String storageStrategy = "FILE";

        @Config.Comment("Database dialect")
        public static String dialect = "mariadb";

        @Config.Comment("Database hostname")
        public static String hostname = "localhost";

        @Config.Comment("Database port")
        public static String port = "3306";

        @Config.Comment("Database name")
        public static String database = "morecommands";

        @Config.Comment("Database user")
        public static String user = "root";

        @Config.Comment("Database user password")
        public static String password = "toor";

        @Config.Comment("Database home table")
        public static String hometable = "HOME";

        @Config.Comment("Database warp table")
        public static String warptable = "WARP";
    }

    /* Handler */
    @Mod.EventBusSubscriber(modid = MoreCommands.MODID)
    private static class EventHandler {

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(MoreCommands.MODID)) {
                ConfigManager.sync(MoreCommands.MODID, Config.Type.INSTANCE);
            }
        }
    }
}