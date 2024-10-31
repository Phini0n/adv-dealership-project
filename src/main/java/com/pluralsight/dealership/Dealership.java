package com.pluralsight.dealership;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;

    List<Vehicle> inventory = new ArrayList<Vehicle>();

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        inventory = new ArrayList<Vehicle>();
    }

    public  void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(int vehicle) {
        inventory.removeIf(vehicles -> vehicles.getVin() == vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public List<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max) {
        List<Vehicle> filtered = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice().compareTo(min) >= 0 && vehicle.getPrice().compareTo(max) <= 0 ) {
                filtered.add(vehicle);
            }
        }
        return filtered;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> filtered = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                filtered.add(vehicle);
            }
        }
        return filtered;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> filtered = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                filtered.add(vehicle);
            }
        }
        return filtered;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> filtered = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                filtered.add(vehicle);
            }
        }
        return filtered;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> filtered = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                filtered.add(vehicle);
            }
        }
        return filtered;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> filtered = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                filtered.add(vehicle);
            }
        }
        return filtered;
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
}
