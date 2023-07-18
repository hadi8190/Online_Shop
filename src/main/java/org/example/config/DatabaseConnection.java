package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;

    private DatabaseConnection() {
    }

    public static Connection getInstance() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DatabaseConfig.URL,
                    DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
        }
        return connection;
    }
}
