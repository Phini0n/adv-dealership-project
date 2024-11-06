package com.pluralsight.dealership;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private final List<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        inventory = new ArrayList<Vehicle>();
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + "|" + address + "|" + phone;
    }

    // Methods
    public  void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    // TODO: fix. it doesn't make sense, why am I using an int here ?
    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    // Uses varargs to filter w/ Predicates
    public List<Vehicle> filterVehicles(Predicate<Vehicle>... args) {
        Predicate<Vehicle> combinedPredicate = vehicle -> true; // All Vehicles
        for (Predicate<Vehicle> arg : args) {
            combinedPredicate = combinedPredicate.and(arg);
        }

        return inventory.stream()
                .filter(combinedPredicate)
                .toList();
    }

    // Filtering Predicates
    public static Predicate<Vehicle> byVehiclePrice(BigDecimal min, BigDecimal max) {
        return vehicle -> vehicle.getPrice().compareTo(min) >= 0
                        && vehicle.getPrice().compareTo(max) <= 0;
    }

    public static Predicate<Vehicle> byVehicleMakeModel (String make, String model) {
        return vehicle -> vehicle.getMake().equalsIgnoreCase(make)
                && vehicle.getModel().equalsIgnoreCase(model);
    }

    public static Predicate<Vehicle> byVehicleYear(int min, int max) {
        return vehicle -> vehicle.getYear() >= min && vehicle.getYear() <= max;
    }

    public static Predicate<Vehicle> byVehicleColor(String color) {
        return vehicle -> vehicle.getColor().equalsIgnoreCase(color);
    }

    public static Predicate<Vehicle> byVehicleMileage(int min, int max) {
        return vehicle -> vehicle.getOdometer() >= min && vehicle.getOdometer() <= max;
    }

    public static Predicate<Vehicle> byVehicleType(String vehicleType) {
        return vehicle -> vehicle.getVehicleType().equalsIgnoreCase(vehicleType);
    }
}
