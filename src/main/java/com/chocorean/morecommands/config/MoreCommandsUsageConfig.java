package com.chocorean.morecommands.config;

public class MoreCommandsUsageConfig {
    private final MoreCommandsConfig config;
    private static final String CATEGORY_USAGE = "usage";

    private String backUsage;
    private String delwarpUsage;
    private String enderchestUsage;
    private String gamemodeUsage;
    private String homeUsage;
    private String invseeUsage;
    private String killAllUsage;
    private String sethomeUsage;
    private String setspawnUsage;
    private String setwarpUsage;
    private String spawnUsage;
    private String tpahereUsage;
    private String tpaUsage;
    private String vanishUsage;
    private String warpUsage;
    private String warpsUsage;

    MoreCommandsUsageConfig(MoreCommandsConfig config) {
        this.config = config;
    }

    void loadProperties() {
        this.backUsage = this.config.getProperty(CATEGORY_USAGE, "back").getString();
        this.delwarpUsage = this.config.getProperty(CATEGORY_USAGE, "delwarp").getString();
        this.enderchestUsage = this.config.getProperty(CATEGORY_USAGE, "enderchest").getString();
        this.gamemodeUsage = this.config.getProperty(CATEGORY_USAGE, "gamemode").getString();
        this.homeUsage = this.config.getProperty(CATEGORY_USAGE, "home").getString();
        this.invseeUsage = this.config.getProperty(CATEGORY_USAGE, "invsee").getString();
        this.killAllUsage = this.config.getProperty(CATEGORY_USAGE, "killall").getString();
        this.sethomeUsage = this.config.getProperty(CATEGORY_USAGE, "sethome").getString();
        this.setspawnUsage = this.config.getProperty(CATEGORY_USAGE, "setspawn").getString();
        this.setwarpUsage = this.config.getProperty(CATEGORY_USAGE, "setwarp").getString();
        this.spawnUsage = this.config.getProperty(CATEGORY_USAGE, "spawn").getString();
        this.tpahereUsage = this.config.getProperty(CATEGORY_USAGE, "tpahere").getString();
        this.tpaUsage = this.config.getProperty(CATEGORY_USAGE, "tpa").getString();
        this.vanishUsage = this.config.getProperty(CATEGORY_USAGE, "vanish").getString();
        this.warpUsage = this.config.getProperty(CATEGORY_USAGE, "warp").getString();
        this.warpsUsage = this.config.getProperty(CATEGORY_USAGE, "warps").getString();
    }

    public String getBackUsage() {
        return backUsage;
    }

    public String getDelwarpUsage() {
        return delwarpUsage;
    }

    public String getEnderchestUsage() {
        return enderchestUsage;
    }

    public String getGamemodeUsage() {
        return gamemodeUsage;
    }

    public String getHomeUsage() {
        return homeUsage;
    }

    public String getKillAllUsage() {
        return killAllUsage;
    }

    public String getInvseeUsage() {
        return invseeUsage;
    }

    public String getSethomeUsage() {
        return sethomeUsage;
    }

    public String getSetspawnUsage() {
        return setspawnUsage;
    }

    public String getSetwarpUsage() {
        return setwarpUsage;
    }

    public String getSpawnUsage() {
        return spawnUsage;
    }

    public String getTpahereUsage() {
        return tpahereUsage;
    }

    public String getTpaUsage() {
        return tpaUsage;
    }

    public String getVanishUsage() {
        return vanishUsage;
    }

    public String getWarpUsage() {
        return warpUsage;
    }

    public String getWarpsUsage() {
        return warpsUsage;
    }
}
