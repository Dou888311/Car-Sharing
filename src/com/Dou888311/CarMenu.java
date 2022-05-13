package com.Dou888311;

import java.sql.*;
import java.util.Scanner;

public class CarMenu {

    private int companyId;
    private String companyName;


    public CarMenu(int id) {
        companyId = id;
    }
    CarDaoImpl carDao = new CarDaoImpl();

    public void getCompanyName() {
        try(Connection conn = DriverManager.getConnection(H2DataBase.getUrl())) {
            try (Statement stmt = conn.createStatement()) {
                ResultSet res = stmt.executeQuery("SELECT * FROM COMPANY;");
                while(res.next()) {
                    if (res.getInt(1) == companyId) {
                        companyName = res.getString(2);
                    }
                }

            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void carMenu() {
        getCompanyName();
        System.out.println("'" + companyName + "' company");
        while (true) {
            System.out.printf("1. Car list \n" +
                    "2. Create a car\n" +
                    "0. Back \n");
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();
            switch (userInput) {
                case "1":
                    carDao.getCars(companyId);
                    break;
                case "2":
                    carDao.addCar(companyId);
                    break;
                case "0":
                    return;
            }
        }
    }
}
