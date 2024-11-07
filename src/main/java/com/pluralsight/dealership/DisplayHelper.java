package com.pluralsight.dealership;

import java.util.ArrayList;

public final class DisplayHelper {

    // TODO: fix. Do not need a class to display whole program. Make code as generic as possible, don't make methods that are used once.
    private static boolean firstRun = true;

    private DisplayHelper() {}

    public static void displayMainMenu(Dealership dealership) {

    }

    public static void displayFilterMenu() {
        System.out.print(
                """
                        
                        Filter Menu
                        1) Price Range
                        2) Make / Model
                        3) Year Range
                        4) Color
                        5) Mileage Range
                        6) Vehicle Type (car, truck, SUV, van)
                        7) Return to Main Menu
                        Enter:\s"""
        );
    }

    public static void displayGoodbye() {
        System.out.println("\nThank you for browsing!");
    }

    public static void invalidEntry() {
        System.out.println("\nInvalid choice!");
    }

    public static void displayFilterRequest(int filter) {
        switch (filter) {
            case 6:
                System.out.print("Please enter the vehicle type (EX. sedan, SUV, etc.): ");
        }
    }

    public static void displayAddRemoveVehicle(int index) {
        switch (index) {
            case -1:
                System.out.print("Enter the VIN of the vehicle you'd like to remove: ");
                break;
            case 0:
                System.out.print("Enter the vehicle VIN: ");
                break;
            case 1:
                System.out.print("Enter the year: ");
                break;
            case 2:
                System.out.print("Enter the make: ");
                break;
            case 3:
                System.out.print("Enter the model: ");
                break;
            case 4:
                System.out.print("Enter the type of car: ");
                break;
            case 5:
                System.out.print("Enter the color of the car: ");
                break;
            case 6:
                System.out.print("Enter the odometer reading: ");
                break;
            case 7:
                System.out.print("Enter the price: ");
                break;
        }
    }

    public static void vehicleConfirmation(boolean removed, Vehicle vehicle) {
        if (!removed) {
            System.out.println(vehicle.getVin() + " was added successfully!");
        } else {
            System.out.println(" was removed successfully!");
        }
    }

    public static void vehicleConfirmation(boolean removed, int vin) {
        if (!removed) {
            System.out.println(vin + " was added successfully!");
        } else {
            System.out.println(vin + " was removed successfully!");
        }
    }

    public static void displayRemoveVehicle() {
        System.out.print("Would you like to remove a vehicle?");
    }

    // TODO: Remove this if unnecessary
    public static void displayVehicles(ArrayList<Vehicle> vehicles) {
        System.out.println("\nVehicles:");
        System.out.println("VIN|YEAR|MAKE|MODEL|TYPE|COLOR|ODOMETER|PRICE");
        vehicles.forEach(System.out::println);
    }
}
