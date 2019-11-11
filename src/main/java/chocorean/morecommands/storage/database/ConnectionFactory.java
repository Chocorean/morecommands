package chocorean.morecommands.storage.database;

import chocorean.morecommands.config.MoreCommandsConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(String.format("jdbc:%s://%s:%s/%s",
                    MoreCommandsConfig.DatabaseCategory.dialect,
                    MoreCommandsConfig.DatabaseCategory.hostname,
                    MoreCommandsConfig.DatabaseCategory.port,
                    MoreCommandsConfig.DatabaseCategory.database),
                    MoreCommandsConfig.DatabaseCategory.user,
                    MoreCommandsConfig.DatabaseCategory.password);
        } catch (SQLException ex) {
            throw new RuntimeException("morecommands.database.connecterror", ex);
        }
    }
}
