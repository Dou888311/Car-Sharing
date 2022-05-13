package com.Dou888311;
import java.sql.*;
import java.util.Scanner;

public class RentedCarDaoImpl implements RentedCarDAO {

    public void rentACar(int customerId) {

        try(Connection conn = DriverManager.getConnection(H2DataBase.getUrl())) {
            try (Statement stmt = conn.createStatement()) {
                ResultSet resTest = stmt.executeQuery("SELECT RENTED_CAR_ID FROM CUSTOMER WHERE ID = " + customerId + ";");
                resTest.next();
                int check = resTest.getInt(1);
                if (check != 0) {
                    System.out.println("You've already rented a car!");
                    return;
                }
            }
        } catch (SQLException e) {
            e.getMessage();
        }
            CompanyDaoImpl comp = new CompanyDaoImpl();
            comp.getAllCompanies();
            Scanner sc = new Scanner(System.in);
            int id = Integer.parseInt(sc.nextLine());
            CarDaoImpl card = new CarDaoImpl();
            card.getCars(id);
            int carId = Integer.parseInt(sc.nextLine());

            try (Connection conn = DriverManager.getConnection(H2DataBase.getUrl())) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate("UPDATE CUSTOMER \n" +
                            "SET RENTED_CAR_ID = " + carId + " WHERE ID = " + customerId + ";");
                    ResultSet res = stmt.executeQuery("SELECT NAME FROM CAR WHERE ID = " + carId + ";");
                    res.next();
                    System.out.println("You rented '" + res.getString(1) + "'");
                }
            } catch (SQLException e) {
                e.getMessage();
            }
    }

    public void myRentedCar(int customerId) {
            try (Connection conn = DriverManager.getConnection(H2DataBase.getUrl())) {
                try (Statement stmt = conn.createStatement()) {
                    ResultSet res = stmt.executeQuery("SELECT RENTED_CAR_ID FROM CUSTOMER WHERE ID = " + customerId + ";");
                    int carId;
                    res.next();
                    carId = res.getInt(1);
                    if (carId == 0) {
                        System.out.println("You didn't rent a car!");
                        return;
                    }
                    res = stmt.executeQuery("SELECT NAME FROM CAR WHERE ID = " + carId + ";");
                    res.next();
                    String carName = res.getString(1);
                    res = stmt.executeQuery("SELECT COMPANY_ID FROM CAR WHERE ID = " + carId + ";");
                    res.next();
                    String companyId = res.getString(1);
                    res = stmt.executeQuery("SELECT NAME FROM COMPANY WHERE ID = " + companyId + ";");
                    res.next();
                    String companyName = res.getString(1);
                    System.out.printf("Your rented car: \n" +
                            "%s\n" +
                            "Company:\n" +
                            "%s\n", carName, companyName);

                }
            } catch (SQLException e) {
                e.getMessage();
            }
    }

    public void returnACar(int customerId) {
        try (Connection conn = DriverManager.getConnection(H2DataBase.getUrl())) {
            try (Statement stmt = conn.createStatement()) {
                ResultSet res = stmt.executeQuery("SELECT RENTED_CAR_ID FROM CUSTOMER WHERE ID = " + customerId + ";");
                int carId;
                res.next();
                carId = res.getInt(1);
                if (carId == 0) {
                    System.out.println("You didn't rent a car!");
                    return;
                } else {
                    stmt.executeUpdate("UPDATE CUSTOMER \n" +
                            "SET RENTED_CAR_ID = NULL WHERE ID = " + customerId + ";");
                    System.out.println("You've returned a rented car!");
                }
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
