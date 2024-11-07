package com.pluralsight.contract;

import com.pluralsight.dealership.Vehicle;

import java.math.BigDecimal;

public class SalesContract extends Contract {
    private final BigDecimal SALES_TAX_AMOUNT = new BigDecimal(5);
    private final BigDecimal RECORDING_FEE = new BigDecimal(100);
    private final BigDecimal processingFee;
    private final Boolean wantsToFinance;

    public SalesContract(String date, String name, String email, Vehicle vehicleSold, boolean wantsToFinance) {
        super(date, name, email, vehicleSold);
        this.processingFee = this.getVehicleSold().getPrice() // If greater than/equal to 10,000 = 495, else 295
                .compareTo(new BigDecimal(10000)) >= 0 ? new BigDecimal(495) : new BigDecimal(295);
        this.wantsToFinance = wantsToFinance;
    }

    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal finalPrice = this.getVehicleSold()
                .getPrice()
                .add(this.RECORDING_FEE)
                .add(this.processingFee);

        finalPrice = finalPrice.add(percentage(finalPrice, SALES_TAX_AMOUNT));

        if (wantsToFinance) {
            finalPrice = finalPrice.add(percentage(finalPrice, getMonthlyPayment()));
        }

        return finalPrice;
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        if (this.wantsToFinance) {
            if (processingFee.compareTo(new BigDecimal(495)) == 0) {
                return new BigDecimal("4.25");
            } else {
                return new BigDecimal("5.25");
            }
        } else {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public String toString() {
        return "SALE" + "|" + this.getDate() + "|" + this.getName()
                + "|" + this.getEmail() + "|" + this.getVehicleSold().getVin()
                + "|" + getVehicleSold().getYear() + "|" +
                getVehicleSold().getMake() + "|" + getVehicleSold().getModel()
                + "|" + getVehicleSold().getVehicleType() + "|"
                + getVehicleSold().getColor() + "|" + getVehicleSold().getOdometer()
                + "|" + getTotalPrice();

    }
}
