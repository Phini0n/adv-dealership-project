package com.pluralsight.contract;

import com.pluralsight.dealership.Vehicle;

import java.math.BigDecimal;

public class LeaseContract extends Contract {
    private BigDecimal expectedEndingValue;
    private BigDecimal leaseFee;

    public LeaseContract(String date, String name, String email, Vehicle vehicleSold, boolean wantsToFinance) {
        super(date, name, email, vehicleSold);
        this.expectedEndingValue = percentage(this.getVehicleSold().getPrice(), new BigDecimal(50));
        this.leaseFee = percentage(this.getVehicleSold().getPrice(), new BigDecimal(7));
    }

    @Override
    public BigDecimal getTotalPrice() {
        return this.getVehicleSold().getPrice().add(expectedEndingValue).add(leaseFee).add(getMonthlyPayment());
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        return percentage(this.getVehicleSold().getPrice(), new BigDecimal(5));
    }
}
