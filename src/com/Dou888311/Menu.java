package com.Dou888311;

import java.util.Scanner;

public class Menu {

    CustomerDaoImpl customerDao = new CustomerDaoImpl();

    public void menu() {
        while (true) {
            System.out.printf("1. Log in as a manager \n" +
                    "2. Log in as a customer \n" +
                    "3. Create a customer \n" +
                    "0. Exit \n");
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();
            switch (userInput) {
                case "0":
                    return;
                case "1":
                    SubMenu submenu = new SubMenu();
                    submenu.subMenu();
                    break;
                case "2" :
                    if(customerDao.chooseCustomer()) {
                        int customerId = Integer.parseInt(sc.nextLine());
                        if (customerId != 0) {
                            CustomerMenu customerMenu = new CustomerMenu();
                            customerMenu.customerMenu(customerId);
                        }
                    }
                    break;
                case "3" :
                    customerDao.createCustomer();
                    break;
            }
        }
    }
}
