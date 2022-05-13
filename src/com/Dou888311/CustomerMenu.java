package com.Dou888311;

import java.util.Scanner;

public class CustomerMenu {

    RentedCarDaoImpl carDao = new RentedCarDaoImpl();

    public void customerMenu(int id) {
        while (true) {
            System.out.printf("1. Rent a car \n" +
                    "2. Return a rented car \n" +
                    "3. My rented car \n" +
                    "0. Back \n");
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();
            switch (userInput) {
                case "1" :
                    carDao.rentACar(id);
                    break;
                case "2" :
                    carDao.returnACar(id);
                    break;
                case "3" :
                    carDao.myRentedCar(id);
                    break;
                case "0" :
                    return;
            }

        }
    }
}
