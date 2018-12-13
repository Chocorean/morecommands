package com.chocorean.morecommands.storage.database;

import com.chocorean.morecommands.MoreCommands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(String.format("jdbc:%s://%s:%s/%s",
                    MoreCommands.getConfig().getDialect(),
                    MoreCommands.getConfig().getHost(),
                    MoreCommands.getConfig().getPort(),
                    MoreCommands.getConfig().getDatabase()),
                    MoreCommands.getConfig().getUser(),
                    MoreCommands.getConfig().getPassword());
        } catch (SQLException ex) {
            throw new RuntimeException(MoreCommands.getConfig().getDatabaseErrorMessage(), ex);
        }
    }
}
