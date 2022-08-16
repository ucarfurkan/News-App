package com.newsapp.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    Connection connect = null;

    public Connection connectDB() throws SQLException {
        if (this.connect == null || this.connect.isClosed()) {
            try {
                this.connect = DriverManager.getConnection(Config.DB_URL, Config.USER, Config.PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.connect;
    }

    public static Connection getInstance() throws SQLException {
        DatabaseConnector db = new DatabaseConnector();
        return db.connectDB();
    }
}
