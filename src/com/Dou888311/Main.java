package com.Dou888311;

public class Main {

    public static void main(String[] args) {
        String DBName = "testDataBase";
        if (args.length > 1) {
            if (args[0].equals("-databaseFileName")) {
                DBName = args[1];
            }
        }
        H2DataBase base = new H2DataBase(DBName);
        base.createTable();
        base.createCarTable();
        base.createCustomerTable();
        Menu menu = new Menu();
        menu.menu();
    }
}
