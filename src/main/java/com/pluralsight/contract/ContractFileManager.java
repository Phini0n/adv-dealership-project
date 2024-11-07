package com.pluralsight.contract;

import com.pluralsight.dealership.Dealership;
import com.pluralsight.dealership.Vehicle;

import java.io.*;
import java.math.BigDecimal;

public final class ContractFileManager {
    private static final File FILE = new File("data/contracts.csv");

    private ContractFileManager() {}


    public static void saveContract(Contract contract) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE));
            if (contract instanceof LeaseContract) {
                bufferedWriter.write("LEASE" + "|");
                bufferedWriter.write(contract.toString());
            } else {
                bufferedWriter.write("SALE" + "|");
                bufferedWriter.write(contract.toString());
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Returning to Main Menu");
        }
    }

    public static String getFileLocation() {
        return FILE.getAbsolutePath();
    }
}
