package com.Dou888311;

import java.sql.*;
import java.util.Scanner;

public class CompanyDaoImpl implements CompanyDAO {

    private String URL = H2DataBase.getUrl();
    private String DBName = H2DataBase.getDBName();

    public boolean getAllCompanies() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            try (Statement stmt = conn.createStatement()) {
                ResultSet res = stmt.executeQuery("SELECT * FROM " + "COMPANY" + ";");
                if (res.next() == false) {
                    System.out.println("The company list is empty!");
                    return false;
                } else {
                    System.out.println("Choose the company: ");
                    res = stmt.executeQuery("SELECT ID, NAME FROM " + "COMPANY" + ";");
                    while (res.next()) {
                        System.out.println(res.getInt(1) + ". " + res.getString(2));
                    }
                    System.out.println("0. Back");
                }
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return true;
    }
    public void addNewCompany() {
        String sqlQuerry = "INSERT INTO " + "COMPANY" + " (NAME)\n" +
                "VALUES (?);";
        try(Connection conn = DriverManager.getConnection(URL)) {
            try(PreparedStatement stmt = conn.prepareStatement(sqlQuerry)) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the company name:");
                stmt.setString(1, sc.nextLine());
                stmt.executeUpdate();
                System.out.println("The company was created!");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
