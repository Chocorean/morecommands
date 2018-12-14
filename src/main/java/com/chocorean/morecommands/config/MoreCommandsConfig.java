package com.chocorean.morecommands.config;

import com.chocorean.morecommands.MoreCommands;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class MoreCommandsConfig {
    private Configuration config;
    private static final String CATEGORY_GENERAL = "general";
    private static final String CATEGORY_USAGE = "command usage";
    private static final String CATEGORY_MESSAGE = "message";
    private static final String CATEGORY_DATABASE = "database";

    // commands
    private boolean isHomeEnabled = true;
    private boolean isWarpEnabled = true;
    private boolean isInvseeEnabled = true;
    private boolean isBackEnabled = true;
    private boolean isTpaEnabled = true;
    private boolean isVanishEnabled = true;
    private boolean isSpawnEnabled = true;

    // commands properties
    private String backUsage = "/back - Teleport you to your previous location.";
    private String homeUsage = "/home - Teleport you to your home.";
    private String invseeUsage = "/invsee <player> - Allow you to access a player's inventory.";
    private String sethomeUsage = "/sethome - Set your home's location (only in overworld).";
    private String setspawnUsage = "/setspawn - Set world's spawn at your current location.";
    private String setwarpUsage = "/setwarp <warp name> - Create a new warp at your current location.";
    private String spawnUsage = "/spawn - Teleport you to the world's spawn.";
    private String tpaUsage = "/tpa <player> - Ask a player if he/she wants you to be teleported to him/her location.";
    private String tpahereUsage = "/tpahere player - Ask a player if he/she wants to be teleported to your current location.";
    private String vanishUsage = "/vanish - Hide you from other players (do it once more to be visible).";
    private String warpUsage = "/warp <warp name> - Warp you if the location exists.";
    private String warpsUsage = "/warps - List available warps.";

    // displayed messages
    private String databaseErrorMessage = "Unable to connect to the database.";
    private String warpNotFoundMessage = "Warp %s has not been found.";
    private String homeNotFoundMessage = "Your home has not been found.";
    private String onWarpMessage = "Whoosh !";
    private String onSpawnMessage = "Whoosh !";
    private String onTpaRequestSrcMessage = "A request has been sent to %s.";
    private String onTpaRequestDestMessage = "%s would like to join you. Type /tpahere <yes|no> to answer.";
    private String onTpahereRequestSrcMessage = "%s would like you to join him. Type /tpa <yes|no> to answer.";
    private String onTpahereRequestDestMessage = "A request has been sent to %s.";
    private String onTpDenyMessage = "%s denied your request.";
    private String onHomeWrongDimensionMessage = "You must be in the overworld to use /home.";
    private String onHomeSetMessage = "Your home has been set.";

    // database
    private String storageStrategy = "FILE";
    private String dialect = "mariadb";
    private String host = "localhost";
    private String port = "3306";
    private String database = "morecommands";
    private String user = "root";
    private String password = "toor";
    private String homeTable = "HOME";
    private String warpTable = "WARP";

    public MoreCommandsConfig(File config) {
        this(new Configuration(config));
    }

    private MoreCommandsConfig(Configuration config) {
        this.config = config;
        this.config.load();

        config.addCustomCategoryComment(CATEGORY_GENERAL, "MoreCommands v" + MoreCommands.VERSION + "\n"+
                " Github link\n" +
                "  - https://github.com/Chocorean/morecommands\n" +
                " Authors\n" +
                "   - Chocorean");

        try { // Read props from config
            /* general category */
            Property isHomeEnabledProp = config.get(MoreCommandsConfig.CATEGORY_GENERAL,
                    "isHomeEnabled",
                    "true",
                    "Enable or disable /home and /sethome commands");
            isHomeEnabled = isHomeEnabledProp.getBoolean();

            Property isWarpEnabledProp = config.get(MoreCommandsConfig.CATEGORY_GENERAL,
                    "isWarpEnabled",
                    "true",
                    "Enable or disable /warp, /setwarp and /warps commands");
            isWarpEnabled = isWarpEnabledProp.getBoolean();

            Property isInvseeEnabledProp = config.get(MoreCommandsConfig.CATEGORY_GENERAL,
                    "isInvseeEnabled",
                    "true",
                    "Enable or disable /invsee command");
            isInvseeEnabled = isInvseeEnabledProp.getBoolean();

            Property isBackEnabledProp = config.get(MoreCommandsConfig.CATEGORY_GENERAL,
                    "isBackEnabled",
                    "true",
                    "Enable or disable /back command");
            isBackEnabled = isBackEnabledProp.getBoolean();

            Property isTpaEnabledProp = config.get(MoreCommandsConfig.CATEGORY_GENERAL,
                    "isTpaEnabled",
                    "true",
                    "Enable or disable /tpa commands");
            isTpaEnabled = isTpaEnabledProp.getBoolean();

            Property isVanishEnabledProp = config.get(MoreCommandsConfig.CATEGORY_GENERAL,
                    "isVanishEnabled",
                    "true",
                    "Enable or disable /vanish command");
            isVanishEnabled = isVanishEnabledProp.getBoolean();

            Property isSpawnEnabledProp = config.get(MoreCommandsConfig.CATEGORY_GENERAL,
                    "isSpawnEnabled",
                    "true",
                    "Enable or disable /spawn command");
            isSpawnEnabled = isSpawnEnabledProp.getBoolean();

            /* commands properties */
            Property backUsageProp = config.get(MoreCommandsConfig.CATEGORY_USAGE,
                    "backUsage",
                    "/back - Teleport you to your previous location.",
                    "/back usage");
            backUsage = backUsageProp.getString();

            Property homeUsageProp = config.get(MoreCommandsConfig.CATEGORY_USAGE,
                    "homeUsage",
                    "/home - Teleport you to your home.",
                    "/home usage");
            homeUsage = homeUsageProp.getString();

            Property invseeUsageProp = config.get(MoreCommandsConfig.CATEGORY_USAGE,
                    "invseeUsage",
                    "/invsee <player> - Allow you to access a player's inventory.",
                    "/invsee usage");
            invseeUsage = invseeUsageProp.getString();

            Property sethomeUsageProp = config.get(MoreCommandsConfig.CATEGORY_USAGE,
                    "sethomeUsage",
                    "/sethome - Set your home's location (only in overworld).",
                    "/sethome usage");
            sethomeUsage = sethomeUsageProp.getString();

            Property setspawnUsageProp = config.get(MoreCommandsConfig.CATEGORY_USAGE,
                    "setspawn",
                    "/setspawn - Set world's spawn at your current location.",
                    "/setspawn usage");
            setspawnUsage = setspawnUsageProp.getString();

            Property setwarpUsageProp = config.get(MoreCommandsConfig.CATEGORY_USAGE,
                    "setwarpUsage",
                    "/setwarp <warp name> - Create a new warp at your current location.",
                    "/setwarp usage");
            setwarpUsage = setwarpUsageProp.getString();

            Property spawnUsageProp = config.get(MoreCommandsConfig.CATEGORY_USAGE,
                    "spawnUsage",
                    "/spawn - Teleport you to the world's spawn.",
                    "/spawn usage");
            spawnUsage = spawnUsageProp.getString();

            Property tpaUsageProp = config.get(MoreCommandsConfig.CATEGORY_USAGE,
                    "tpaUsage",
                    "/tpa <player> - Ask a player if he/she wants you to be teleported to him/her location.",
                    "/tpa usage");
            tpaUsage = tpaUsageProp.getString();

            Property tpahereUsageProp = config.get(MoreCommandsConfig.CATEGORY_USAGE,
                    "tpahereUsage",
                    "/tpahere player - Ask a player if he/she wants to be teleported to your current location.",
                    "/tpahere usage");
            tpahereUsage = tpahereUsageProp.getString();

            Property vanishUsageProp = config.get(MoreCommandsConfig.CATEGORY_USAGE,
                    "vanishUsage",
                    "/vanish - Hide you from other players (do it once more to be visible).",
                    "/vanish usage");
            vanishUsage = vanishUsageProp.getString();

            Property warpUsageProp = config.get(MoreCommandsConfig.CATEGORY_USAGE,
                    "warpUsage",
                    "/warp <warp name> - Warp you if the location exists.",
                    "/warp usage");
            warpUsage = warpUsageProp.getString();

            Property warpsUsageProp = config.get(MoreCommandsConfig.CATEGORY_USAGE,
                    "warpsUsage",
                    "/warps - List available warps.",
                    "/warps usage");
            warpsUsage = warpsUsageProp.getString();

            /* message category */
            Property databaseErrorProp = config.get(MoreCommandsConfig.CATEGORY_MESSAGE,
                    "databaseErrorMessage",
                    "Unable to connect to the database.",
                    "Message displayed when server fails to connect to the database.");
            databaseErrorMessage = databaseErrorProp.getString();

            Property warpNotFoundProp = config.get(MoreCommandsConfig.CATEGORY_MESSAGE,
                    "warpNotFoundMessage",
                    "Warp %s has not been found.",
                    "Message displayed when a warp is not found. %s represent the warp's name.");
            warpNotFoundMessage = warpNotFoundProp.getString();

            Property homeNotFoundProp = config.get(MoreCommandsConfig.CATEGORY_MESSAGE,
                    "homeNotFoundMessage",
                    "Your home has not been found.",
                    "Message displayed when a home is not found.");
            homeNotFoundMessage = homeNotFoundProp.getString();

            Property onWarpProp = config.get(MoreCommandsConfig.CATEGORY_MESSAGE,
                    "onWarpMessage",
                    "Whoosh !",
                    "Message displayed when a /warp is successfully done.");
            onWarpMessage = onWarpProp.getString();

            Property onSpawnProp = config.get(MoreCommandsConfig.CATEGORY_MESSAGE,
                    "onSpawnMessage",
                    "Whoosh !",
                    "Message displayed when a /spawn is successfully done.");
            onSpawnMessage = onSpawnProp.getString();

            Property onTpaRequestSrcProp = config.get(MoreCommandsConfig.CATEGORY_MESSAGE,
                    "onTpaRequestSrc",
                    "A request has been sent to %s.",
                    "Message displayed to the sender when he/she types /tpa <target>.");
            onTpaRequestSrcMessage = onTpaRequestSrcProp.getString();

            Property onTpaRequestDestProp = config.get(MoreCommandsConfig.CATEGORY_MESSAGE,
                    "onTpaRequestDest",
                    "%s would like to join you. Type /tpahere yes/no to answer.",
                    "Message displayed to the target when someone types /tpa <target>.");
            onTpaRequestDestMessage = onTpaRequestDestProp.getString();

            Property onTpahereRequestSrcProp = config.get(MoreCommandsConfig.CATEGORY_MESSAGE,
                    "onTpahereRequestSrc",
                    "%s would like you to join him. Type /tpa yes/no to answer.",
                    "Message displayed to the sender when he/she types /tpahere <target>.");
            onTpahereRequestSrcMessage = onTpahereRequestSrcProp.getString();

            Property onTpahereRequestDestProp = config.get(MoreCommandsConfig.CATEGORY_MESSAGE,
                    "onTpahereRequestDest",
                    "A request has been sent to %s.",
                    "Message displayed to the target when someone types /tpahere <target>.");
            onTpahereRequestDestMessage = onTpahereRequestDestProp.getString();

            Property onTpDenyProp = config.get(MoreCommandsConfig.CATEGORY_MESSAGE,
                    "onTpDeny",
                    "%s denied your request.",
                    "Message displayed to the sender when someone denies his/her tp request;");
            onTpDenyMessage = onTpDenyProp.getString();

            Property homeWrongDimensionProp = config.get(MoreCommandsConfig.CATEGORY_MESSAGE,
                    "homeWrongDimension",
                    "You must be in the overworld to use /home.",
                    "Message displayed when a player tries /home while not being in the overworld.");
            onHomeWrongDimensionMessage = homeWrongDimensionProp.getString();

            Property homeSetProp = config.get(MoreCommandsConfig.CATEGORY_MESSAGE,
                    "homeSet",
                    "Your home has been set.",
                    "Message displayed when a player set his/her home.");
            onHomeSetMessage = homeSetProp.getString();

            /* database category */
            Property storageStrategyProp = config.get(MoreCommandsConfig.CATEGORY_DATABASE,
                    "storageStrategy",
                    "DATABASE",
                    "Choose storage strategy. Choices are \"DATABASE\" and \"FILE\".");
            storageStrategy = storageStrategyProp.getString();

            Property dialectProp = config.get(MoreCommandsConfig.CATEGORY_DATABASE,
                    "dialect",
                    "mariadb",
                    "Choose database dialect.");
            dialect = dialectProp.getString();

            Property hostProp = config.get(MoreCommandsConfig.CATEGORY_DATABASE,
                    "host",
                    "mariadb",
                    "Choose database hostname.");
            host = hostProp.getString();

            Property portProp = config.get(MoreCommandsConfig.CATEGORY_DATABASE,
                    "port",
                    "3306",
                    "Choose database port to use.");
            port = portProp.getString();

            Property databaseProp = config.get(MoreCommandsConfig.CATEGORY_DATABASE,
                    "database",
                    "morecommands",
                    "Choose database name.");
            database = databaseProp.getString();

            Property userProp = config.get(MoreCommandsConfig.CATEGORY_DATABASE,
                    "user",
                    "root",
                    "Username to connect to the database.");
            user = userProp.getString();

            Property passwordProp = config.get(MoreCommandsConfig.CATEGORY_DATABASE,
                    "password",
                    "toor",
                    "Password to connect to the database.");
            password = passwordProp.getString();

            Property homeTableProp = config.get(MoreCommandsConfig.CATEGORY_DATABASE,
                    "homeTable",
                    "HOME",
                    "Name of the homes' table.");
            homeTable = homeTableProp.getString();

            Property warpTableProp = config.get(MoreCommandsConfig.CATEGORY_DATABASE,
                    "warpTable",
                    "WARP",
                    "Name of the warps' table.");
            warpTable = warpTableProp.getString();

        } catch (Exception e) {
            // keep reading
        } finally {
            if (this.config.hasChanged()) this.config.save();
        }
    }

    // general getters

    public boolean isBackEnabled() {
        return isBackEnabled;
    }

    public boolean isHomeEnabled() {
        return isHomeEnabled;
    }

    public boolean isInvseeEnabled() {
        return isInvseeEnabled;
    }

    public boolean isTpaEnabled() {
        return isTpaEnabled;
    }

    public boolean isVanishEnabled() {
        return isVanishEnabled;
    }

    public boolean isWarpEnabled() {
        return isWarpEnabled;
    }

    public boolean isSpawnEnabled() {
        return isSpawnEnabled;
    }

    // usage getters

    public String getBackUsage() {
        return backUsage;
    }

    public String getHomeUsage() {
        return homeUsage;
    }

    public String getInvseeUsage() {
        return invseeUsage;
    }

    public String getSethomeUsage() {
        return sethomeUsage;
    }

    public String getSpawnUsage() {
        return spawnUsage;
    }

    public String getSetwarpUsage() {
        return setwarpUsage;
    }

    public String getSetspawnUsage() {
        return setspawnUsage;
    }

    public String getTpahereUsage() {
        return tpahereUsage;
    }

    public String getTpaUsage() {
        return tpaUsage;
    }

    public String getWarpUsage() {
        return warpUsage;
    }

    public String getWarpsUsage() {
        return warpsUsage;
    }

    public String getVanishUsage() {
        return vanishUsage;
    }

    // message getters

    public String getDatabaseErrorMessage() {
        return databaseErrorMessage;
    }

    public String getWarpNotFoundMessage() {
        return warpNotFoundMessage;
    }

    public String getHomeNotFoundMessage() {
        return homeNotFoundMessage;
    }

    public String getOnWarpMessage() {
        return onWarpMessage;
    }

    public String getOnSpawnMessage() {
        return onSpawnMessage;
    }

    public String getOnTpaRequestDestMessage() {
        return onTpaRequestDestMessage;
    }

    public String getOnTpaRequestSrcMessage() {
        return onTpaRequestSrcMessage;
    }

    public String getOnTpahereRequestDestMessage() {
        return onTpahereRequestDestMessage;
    }

    public String getOnTpahereRequestSrcMessage() {
        return onTpahereRequestSrcMessage;
    }

    public String getOnTpDenyMessage() {
        return onTpDenyMessage;
    }

    public String getOnHomeWrongDimensionMessage() {
        return onHomeWrongDimensionMessage;
    }

    public String getOnHomeSet() {
        return onHomeSetMessage;
    }

    // database getters

    public String getStorageStrategy() {
        return storageStrategy;
    }

    public String getDatabase() {
        return database;
    }

    public String getDialect() {
        return dialect;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getHomeTable() {
        return homeTable;
    }

    public String getWarpTable() {
        return warpTable;
    }
}
