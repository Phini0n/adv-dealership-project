package com.pluralsight.dealership;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public final class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private Dealership dealership;

    // TODO: fix. Put Enum in separate thing.
    private enum Menu {
        MENU_MAIN,
        MENU_FILTER
    }

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
                    DisplayHelper.displayMainMenu(dealership);
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
                    DisplayHelper.displayError(e);
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
                        case 7: // Return to Main Menu
                            handleDisplay(Menu.MENU_MAIN); // Recursively goes into the main menu
                        default:
                            DisplayHelper.invalidEntry();
                            break;
                    }
                } catch (Exception e) {
                    DisplayHelper.displayError(e);
                    scanner.nextLine();
                }
            }
        }
    }

    private void processGetByPriceRequest() {
        DisplayHelper.displayFilterRequest(1); // Displays filter request for price range.
        String[] priceRange = scanner.nextLine().split("-");
        try {
            DisplayHelper.displayVehicles((ArrayList<Vehicle>) dealership.getVehiclesByPrice(
                    new BigDecimal(priceRange[0]),
                    new BigDecimal(priceRange[1])));
        } catch (Exception e) {
            DisplayHelper.displayError(e);
            scanner.nextLine();
        }
    }

    private void processGetByMakeModelRequest() {
        DisplayHelper.displayFilterRequest(2);
        try {
            String[] makeModel = scanner.nextLine().split(" ");
            DisplayHelper.displayVehicles((ArrayList<Vehicle>) dealership.getVehiclesByMakeModel(
                    makeModel[0],
                    makeModel[1]));
        } catch (Exception e) {
            DisplayHelper.displayError(e);
            scanner.nextLine();
        }
    }

    private void processGetByYearRequest() {
        DisplayHelper.displayFilterRequest(3);
        try {
            String[] yearRange = scanner.nextLine().split("-");
            DisplayHelper.displayVehicles((ArrayList<Vehicle>) dealership.getVehiclesByYear(
                    Integer.parseInt(yearRange[0]),
                    Integer.parseInt(yearRange[1])));
        } catch (Exception e) {
            DisplayHelper.displayError(e);
            scanner.nextLine();
        }
    }

    private void processGetByColorRequest() {
        DisplayHelper.displayFilterRequest(4); // Displays filter request for color.
        DisplayHelper.displayVehicles((ArrayList<Vehicle>) dealership.getVehiclesByColor(scanner.nextLine()));
    }

    private void processGetByMileageRequest() {
        DisplayHelper.displayFilterRequest(5);
        try {
            String[] odometerRange = scanner.nextLine().split("-");
            DisplayHelper.displayVehicles((ArrayList<Vehicle>) dealership.getVehiclesByMileage(
                    Integer.parseInt(odometerRange[0]),
                    Integer.parseInt(odometerRange[1])));
        } catch (Exception e) {
            DisplayHelper.displayError(e);
            scanner.nextLine();
        }
    }

    private void processGetByVehicleTypeRequest() {
        DisplayHelper.displayFilterRequest(6);
        DisplayHelper.displayVehicles((ArrayList<Vehicle>) dealership.getVehiclesByType(scanner.nextLine()));
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
            DisplayHelper.displayError(e);
        }
        DealershipFileManager.saveDealership(dealership);
    }

    private void processRemoveVehicleRequest(){
        try {
            DisplayHelper.displayAddRemoveVehicle(-1);
            int vin = scanner.nextInt();
            dealership.removeVehicle(vin);
            DealershipFileManager.saveDealership(dealership);

            DisplayHelper.vehicleConfirmation(true, vin);
        } catch (Exception e) {
            DisplayHelper.displayError(e);
        }
    }
}
