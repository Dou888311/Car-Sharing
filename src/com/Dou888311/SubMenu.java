package com.Dou888311;

import java.util.Scanner;

public class SubMenu {

    CompanyDaoImpl companyDao = new CompanyDaoImpl();

    public void subMenu() {
        while (true) {
            System.out.printf("1. Company list \n" +
                    "2. Create a company \n" +
                    "0. Back \n");
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();
            switch (userInput) {
                case "1":
                    if(companyDao.getAllCompanies()) {
                        int id = Integer.parseInt(sc.nextLine());
                        if (id != 0) {
                            CarMenu carMenu = new CarMenu(id);
                            carMenu.carMenu();
                        }
                    }
                    break;
                case "2":
                    companyDao.addNewCompany();
                    break;
                case "0":
                    return;
            }
        }
    }
}
