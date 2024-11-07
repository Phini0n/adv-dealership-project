package com.pluralsight.contract;

import com.pluralsight.dealership.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;

// TODO: fix actions after getter & setter
public abstract class Contract {
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private String date;
    private String name;
    private String email;
    private Vehicle vehicleSold;
    private BigDecimal totalPrice;
    private BigDecimal monthlyPayment;

    // Constructor
    public Contract(String date, String name, String email, Vehicle vehicleSold) {
        this.date = date;
        this.name = name;
        this.email = email;
        this.vehicleSold = vehicleSold;
    }

    // Abstract Methods
    public abstract BigDecimal getTotalPrice();

    public abstract BigDecimal getMonthlyPayment();

    // Getters & Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    protected static BigDecimal percentage(BigDecimal base, BigDecimal pct){
        return base.multiply(pct).divide(ONE_HUNDRED);
    }
}
