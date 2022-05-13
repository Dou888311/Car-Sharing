package com.Dou888311;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2DataBase {
    private static String DBName;
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static String URL;

    public H2DataBase(String DBName) {
        this.DBName = DBName;
        URL = "jdbc:h2:D:/LEARN/Project/CarSharing/src/com/Dou888311/db" + DBName + "";
    }

    public void createTable() {
    try {
        Class.forName(JDBC_DRIVER);
        try (Connection conn = DriverManager.getConnection(URL)) {
            try (Statement stmt = conn.createStatement()) {
                conn.setAutoCommit(true);
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS COMPANY (\n" +
                        "ID INT PRIMARY KEY AUTO_INCREMENT,\n" +
                        "NAME VARCHAR NOT NULL UNIQUE \n" +
                        ");");
                stmt.executeUpdate("ALTER TABLE COMPANY ALTER COLUMN ID RESTART WITH 1;");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    } catch (ClassNotFoundException e) {
        System.out.println("hehe");
    }
    }

    public void createCarTable() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            try (Statement stmt = conn.createStatement()) {
                conn.setAutoCommit(true);
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS CAR (\n" +
                        "ID INT PRIMARY KEY AUTO_INCREMENT, \n" +
                        "NAME VARCHAR NOT NULL UNIQUE, \n" +
                        "COMPANY_ID INT NOT NULL," +
                        "CONSTRAINT fk_car FOREIGN KEY (COMPANY_ID)" +
                        "REFERENCES COMPANY (ID));");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void createCustomerTable() {
        try(Connection conn = DriverManager.getConnection(URL)) {
            try (Statement stmt = conn.createStatement()) {
                conn.setAutoCommit(true);
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS CUSTOMER (\n" +
                        "ID INT PRIMARY KEY AUTO_INCREMENT, \n" +
                        "NAME VARCHAR UNIQUE NOT NULL,\n" +
                        "RENTED_CAR_ID INT DEFAULT NULL,\n" +
                        "CONSTRAINT fk_customer FOREIGN KEY (RENTED_CAR_ID) " +
                        "REFERENCES CAR (ID));");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public static String getUrl() {return URL;}
    public static String getDBName() {return DBName;}
}
