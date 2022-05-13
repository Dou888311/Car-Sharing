package com.Dou888311;

public interface RentedCarDAO {
    void rentACar(int customerId);
    void returnACar(int customerId);
    void myRentedCar(int customerId);
}
