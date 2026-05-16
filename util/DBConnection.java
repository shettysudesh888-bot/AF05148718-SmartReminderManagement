package com.jdbc.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    private static String URL;
    private static String USER;
    private static String PASS;

    // Load credentials from db.properties instead of hardcoding them
    static {
        try (InputStream in = DBConnection.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (in != null) {
                Properties props = new Properties();
                props.load(in);
                URL  = props.getProperty("db.url");
                USER = props.getProperty("db.username");
                PASS = props.getProperty("db.password");
            } else {
                System.out.println("[Warning] db.properties not found. Using defaults.");
                URL  = "jdbc:mysql://localhost:3306/reminder_db";
                USER = "root";
                PASS = "";
            }
        } catch (Exception e) {
            System.out.println("[Error] Could not load db.properties: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
