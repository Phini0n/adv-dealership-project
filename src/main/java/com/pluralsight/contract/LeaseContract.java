package com.pluralsight.contract;

import com.pluralsight.dealership.Vehicle;

import java.math.BigDecimal;

public class LeaseContract extends Contract {
    private BigDecimal expectedEndingValue;
    private BigDecimal leaseFee;

    public LeaseContract(String date, String name, String email, Vehicle vehicleSold, boolean wantsToFinance) {
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
