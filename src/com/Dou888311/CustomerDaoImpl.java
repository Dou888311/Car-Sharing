package com.Dou888311;

import java.sql.*;
import java.util.Scanner;

public class CustomerDaoImpl implements CustomerDAO {

    public void createCustomer() {
        System.out.println("Enter the customer name:");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        String querry = "INSERT INTO " + "CUSTOMER" + "(NAME) " +
                "VALUES (?);";

        try (Connection conn = DriverManager.getConnection(H2DataBase.getUrl())) {
            try (PreparedStatement stmt = conn.prepareStatement(querry)) {
                stmt.setString(1, userInput);
                stmt.executeUpdate();
                System.out.println("The customer was added!");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public boolean chooseCustomer() {
        try(Connection conn = DriverManager.getConnection(H2DataBase.getUrl())) {
            try (Statement stmt = conn.createStatement()) {
                ResultSet res = stmt.executeQuery("SELECT * FROM CUSTOMER;");
                if (!res.next()) {
                    System.out.println("The customer list is empty!");
                    return false;
                } else {
                    System.out.println("Customer list: ");
                    res = stmt.executeQuery("SELECT * FROM CUSTOMER;");
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
}
