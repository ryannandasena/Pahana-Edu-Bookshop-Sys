package com.pahanaedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static DBUtil instance;
    private final String URL = "jdbc:mysql://127.0.0.1:3306/pahana_billing?useSSL=false&serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASSWORD = "admin123";

    // Private constructor to enforce singleton
    private DBUtil() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
            System.out.println("✅ MySQL JDBC Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("❌ Error loading MySQL JDBC Driver.");
        }
    }

    // Singleton instance
    public static DBUtil getInstance() {
        if (instance == null) {
            instance = new DBUtil();
        }
        return instance;
    }

    // Return a NEW connection every time
    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        conn.setAutoCommit(true); // Ensure changes are committed automatically
        System.out.println("✅ Connected to database: " + conn.getCatalog());
        return conn;
    }

    // Optional: Test connection
    public static void main(String[] args) {
        try (Connection conn = DBUtil.getInstance().getConnection()) {
            System.out.println("Connection successful: " + (conn != null));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
