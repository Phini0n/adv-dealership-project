package com.pluralsight.dealership;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private Dealership dealership;
    private static boolean firstRun = true;

    // TODO: fix. Put Enum in separate thing.


    private void init() {
        dealership = DealershipFileManager.getDealership();
    }

    public void display() {
        // Initialize dealership
        init();

        // Menu Display
        handleDisplay(Menu.MENU_MAIN);
    }


    // TODO: fix. Recursion is not necessary. look up fibonacci recursive. also can just turn the recursion into a method.
    private void handleDisplay(Menu menuType) {
        int choice = -1;
        int exitValue = 0; // Value that exits the selected menu

        // MAIN MENU
        if (menuType == Menu.MENU_MAIN) {
            exitValue = 99;
            // Main Menu Switch Case
            while (choice != exitValue) {
                try {
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
                                    5) Sell/Lease a Vehicle
                                    99) Exit
                                    Enter:\s"""
                    );
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    // Passing to requests
                    switch (choice) {
                        case 1: // Show All Vehicles
                            processGetAllVehicleRequest();
                            break;
                        case 2: // Filter Vehicles
                            handleDisplay(Menu.MENU_FILTER); // Recursively makes the Filter Menu
                            break;
                        case 3: // Add a Vehicle
                            processAddVehicleRequest();
                            break;
                        case 4: // Remove a Vehicle
                            processRemoveVehicleRequest();
                            // TODO: Implement Sell/Lease Vehicle.
                            System.out.println("Sell/Lease NYI");
                            break;
                        case 5: // Sell/Lease a Vehicle

                            break;
                        case 99: // Exit
                            DisplayHelper.displayGoodbye();
                            scanner.close();
                            System.exit(0);
                            break;
                        default:
                            DisplayHelper.invalidEntry();
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    System.out.println("Returning to Main Menu");
                    scanner.nextLine();
                }
            }

        // FILTER MENU
        } else if (menuType == Menu.MENU_FILTER) {
            exitValue = 7;
            //Filter Menu Switch Case
            while (choice != exitValue) {
                DisplayHelper.displayFilterMenu();
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    // Passing to requests
                    switch (choice) {
                        case 1: // Price Range
                            processGetByPriceRequest();
                            break;
                        case 2: // Make / Model
                            processGetByMakeModelRequest();
                            break;
                        case 3: // Year Range
                            processGetByYearRequest();
                            break;
                        case 4: // Color
                            processGetByColorRequest();
                            break;
                        case 5: // Mileage Range
                            processGetByMileageRequest();
                            break;
                        case 6: // Vehicle Type (car, truck, SUV, van)
                            processGetByVehicleTypeRequest();
                            break;
                        case 7: // Return to Main Menu
                            handleDisplay(Menu.MENU_MAIN); // Recursively goes into the main menu
                        default:
                            DisplayHelper.invalidEntry();
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    System.out.println("Returning to Main Menu");
                    scanner.nextLine();
                }
            }
        }
    }

    private void processGetByPriceRequest() {
        System.out.print("Please enter your minimum price, followed by a hyphen, then " +
                "your maximum price (EX. \"200.00-3000.00\"): ");
        String[] priceRange = scanner.nextLine().trim().split("-");
        try {
            List<Vehicle> filteredVehicles = dealership.filterVehicles(Dealership.byVehiclePrice(
                    new BigDecimal(priceRange[0]),
                    new BigDecimal(priceRange[1])
            ));
            DisplayHelper.displayVehicles(new ArrayList<>(filteredVehicles));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Returning to Main Menu");
//            scanner.nextLine();
        }
    }

    private void processGetByMakeModelRequest() {
        System.out.print("Please enter your make followed by your model (EX. Toyota Camry): ");
        try {
            String[] makeModel = scanner.nextLine().trim().split(" ");
            List<Vehicle> filteredVehicles = dealership.filterVehicles(Dealership.byVehicleMakeModel(
                    makeModel[0],
                    makeModel[1]
            ));
            DisplayHelper.displayVehicles(new ArrayList<>(filteredVehicles));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Returning to Main Menu");
//            scanner.nextLine();
        }
    }

    private void processGetByYearRequest() {
        System.out.print("Please enter the span of years you want to filter by using a hyphen (EX. \"2001-2020\"): ");
        try {
            String[] yearRange = scanner.nextLine().trim().split("-");
            List<Vehicle> filteredVehicles = dealership.filterVehicles(Dealership.byVehicleYear(
                    Integer.parseInt(yearRange[0]),
                    Integer.parseInt(yearRange[1])
            ));
            DisplayHelper.displayVehicles(new ArrayList<>(filteredVehicles));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Returning to Main Menu");
//            scanner.nextLine();
        }
    }

    private void processGetByColorRequest() {
        System.out.print("Please enter the color you want to filter by: ");
        try {
            List<Vehicle> filteredVehicles = dealership.filterVehicles(Dealership.byVehicleColor(
                    scanner.nextLine()
            ));
            DisplayHelper.displayVehicles(new ArrayList<>(filteredVehicles));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Returning to Main Menu");
        }
    }

    private void processGetByMileageRequest() {
        System.out.print("Please enter the span of mileage you want to filter by (EX. \"8000-12000\"): ");
        try {
            String[] odometerRange = scanner.nextLine().split("-");
            List<Vehicle> filteredVehicles = dealership.filterVehicles(Dealership.byVehicleMileage(
                    Integer.parseInt(odometerRange[0]),
                    Integer.parseInt(odometerRange[1])
            ));
            DisplayHelper.displayVehicles(new ArrayList<>(filteredVehicles));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Returning to Main Menu");
//            scanner.nextLine();
        }
    }

    private void processGetByVehicleTypeRequest() {
        System.out.print("Please enter the vehicle type (EX. sedan, SUV, etc.): ");
        try {
            List<Vehicle> filteredVehicles = dealership.filterVehicles(Dealership.byVehicleType(
                    scanner.nextLine()
            ));
            DisplayHelper.displayVehicles(new ArrayList<>(filteredVehicles));
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Returning to Main Menu");
//            scanner.nextLine();
        }
    }

    private void processGetAllVehicleRequest() {
        DisplayHelper.displayVehicles((ArrayList<Vehicle>) dealership.getAllVehicles());
    }

    private void processAddVehicleRequest() {
        try {
            // VIN
            DisplayHelper.displayAddRemoveVehicle(0);
            int vin = scanner.nextInt();
            scanner.nextLine();
            // Year
            DisplayHelper.displayAddRemoveVehicle(1);
            int year = scanner.nextInt();
            scanner.nextLine();
            // Make
            DisplayHelper.displayAddRemoveVehicle(2);
            String make = scanner.nextLine();
            // Model
            DisplayHelper.displayAddRemoveVehicle(3);
            String model = scanner.nextLine();
            // Type
            DisplayHelper.displayAddRemoveVehicle(4);
            String type = scanner.nextLine();
            // Color
            DisplayHelper.displayAddRemoveVehicle(5);
            String color = scanner.nextLine();
            // Odometer
            DisplayHelper.displayAddRemoveVehicle(6);
            int odometer = scanner.nextInt();
            scanner.nextLine();
            // Price -- Setting it to two decimal places
            DisplayHelper.displayAddRemoveVehicle(7);
            BigDecimal price = new BigDecimal(scanner.nextLine()).setScale(2, RoundingMode.HALF_UP);

            Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);

            dealership.addVehicle(newVehicle);

            DisplayHelper.vehicleConfirmation(false, newVehicle);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Returning to Main Menu");
        }
        DealershipFileManager.saveDealership(dealership);
    }

    private void processRemoveVehicleRequest(){
        try {
            DisplayHelper.displayAddRemoveVehicle(-1);
            int vin = scanner.nextInt();
            List<Vehicle> vehicles = dealership.getAllVehicles();
            vehicles.removeIf(vehicle -> vehicle.getVin() == vin);
            DealershipFileManager.saveDealership(dealership);
            DisplayHelper.vehicleConfirmation(true, vin);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Returning to Main Menu");
        }
    }
}
