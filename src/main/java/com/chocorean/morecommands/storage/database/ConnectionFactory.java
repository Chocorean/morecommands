package com.chocorean.morecommands.storage.database;

import com.chocorean.morecommands.MoreCommands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(String.format("jdbc:%s://%s:%s/%s",
                    MoreCommands.getConfig().getDatabaseConfig().getDialect(),
                    MoreCommands.getConfig().getDatabaseConfig().getHost(),
                    MoreCommands.getConfig().getDatabaseConfig().getPort(),
                    MoreCommands.getConfig().getDatabaseConfig().getDatabase()),
                    MoreCommands.getConfig().getDatabaseConfig().getUser(),
                    MoreCommands.getConfig().getDatabaseConfig().getPassword());
        } catch (SQLException ex) {
            throw new RuntimeException(MoreCommands.getConfig().getMessageConfig().getDatabaseErrorMessage(), ex);
        }
    }
}
