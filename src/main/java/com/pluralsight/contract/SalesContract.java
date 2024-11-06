package com.pluralsight.contract;

import com.pluralsight.dealership.Vehicle;

import java.math.BigDecimal;

public class SalesContract extends Contract {

    private final double salesTaxAmount = .05; // 5%
    // private final BigDecimal recordingFee = 100;

    public SalesContract(String date, String name, String email, Vehicle vehicleSold) {
        super(date, name, email, vehicleSold);
    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        return null;
    }
}
