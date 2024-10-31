package com.pluralsight.dealership;

import java.util.ArrayList;

public final class DisplayHelper {

    private static boolean firstRun = true;

    private DisplayHelper() {}

    public static void displayMainMenu(Dealership dealership) {
        if (firstRun) {
            System.out.println("Loaded dealership info from: " + DealershipFileManager.getFileLocation());
            System.out.println();

            System.out.println("Welcome to " + dealership.getName() + " at " + dealership.getAddress() +"!");
            System.out.println("If you have any questions call at the number: " + dealership.getPhone());

            firstRun = false;
        }
        System.out.print(
                """
                        
                        Main Menu
                        1) Show All Vehicles
                        2) Filter Vehicles
                        3) Add a Vehicle
                        4) Remove a Vehicle
                        99) Exit
                        Enter:\s"""
        );
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

    // Displays generic error message used by UserInterface
    public static void displayError(Exception e) {
        System.out.println("Error: " + e);
        System.out.println("Returning to Main Menu");
    }

    public static void invalidEntry() {
        System.out.println("\nInvalid choice!");
    }

    public static void displayFilterRequest(int filter) {
        switch (filter) {
            case 1:
                System.out.print("Please enter your minimum price, followed by a hyphen, then " +
                        "your maximum price (EX. \"200.00-3000.00\"}): ");
                break;
            case 2:
                System.out.print("Please enter your make followed by your model (EX. Toyota Camry): ");
                break;
            case 3:
                System.out.print("Please enter the span of years you want to filter by using a hyphen (EX. \"2001-2020\"): ");
                break;
            case 4:
                System.out.print("Please enter the color you want to filter by: ");
                break;
            case 5:
                System.out.print("Please enter the span of mileage you want to filter by (EX. \"8000-12000\"): ");
                break;
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
        System.out.println();
        System.out.println("Vehicles:");
        System.out.println("VIN|YEAR|MAKE|MODEL|TYPE|COLOR|ODOMETER|PRICE");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }
}
