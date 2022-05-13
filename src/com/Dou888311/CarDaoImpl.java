package com.Dou888311;

import java.sql.*;
import java.util.Scanner;

public class CarDaoImpl implements CarDAO {

    public void getCars(int id) {
        try(Connection conn = DriverManager.getConnection(H2DataBase.getUrl())) {
            try (Statement stmt = conn.createStatement()) {
                ResultSet res = stmt.executeQuery("SELECT * FROM CAR \n" +
                        "WHERE COMPANY_ID = " + id +";");
                if (!res.next()) {
                    System.out.println("The car list is empty!");
                } else {
                    res = stmt.executeQuery("SELECT * FROM CAR \n" +
                            "WHERE COMPANY_ID = " + id +" AND ID NOT IN (SELECT RENTED_CAR_ID " +
                            "FROM CUSTOMER " +
                            "WHERE RENTED_CAR_ID IS NOT NULL);");
                    int i = 1;
                    while (res.next()) {
                        System.out.println(i + ". " + res.getString(2));
                        i++;
                    }
                }
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void addCar(int id) {
        String sqlQuery = "INSERT INTO " +"CAR" + " (NAME, COMPANY_ID) \n" +
                "VALUES(?, ?);";
        try(Connection conn = DriverManager.getConnection(H2DataBase.getUrl())) {
            try(PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the car name:");
                String carName = sc.nextLine();
                stmt.setString(1, carName);
                stmt.setInt(2, id);
                stmt.executeUpdate();
                System.out.println("The car was added!");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
