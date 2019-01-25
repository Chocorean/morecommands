package com.chocorean.morecommands.config;

public class MoreCommandsDatabaseConfig {
    MoreCommandsConfig config;
    private static final String CATEGORY_DATABASE = "database";

    private String storageStrategy;
    private String dialect;
    private String host;
    private String port;
    private String database;
    private String user;
    private String password;
    private String homeTable;
    private String warpTable;

    public MoreCommandsDatabaseConfig(MoreCommandsConfig config) {
        this.config = config;
    }

    public void loadProperties() {
        this.storageStrategy = this.config.getProperty(CATEGORY_DATABASE, "storageStrategy").getString();
        this.dialect = this.config.getProperty(CATEGORY_DATABASE, "dialect").getString();
        this.host = this.config.getProperty(CATEGORY_DATABASE, "host").getString();
        this.port = this.config.getProperty(CATEGORY_DATABASE, "port").getString();
        this.database = this.config.getProperty(CATEGORY_DATABASE, "database").getString();
        this.user = this.config.getProperty(CATEGORY_DATABASE, "user").getString();
        this.password = this.config.getProperty(CATEGORY_DATABASE, "password").getString();
        this.homeTable = this.config.getProperty(CATEGORY_DATABASE, "homeTable").getString();
        this.warpTable = this.config.getProperty(CATEGORY_DATABASE, "warpTable").getString();
    }

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
