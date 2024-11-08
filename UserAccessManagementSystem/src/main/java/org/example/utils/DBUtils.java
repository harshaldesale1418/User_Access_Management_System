package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static final String URL = "jdbc:postgresql://localhost:5432/access_management_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Harshal@1418";
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
