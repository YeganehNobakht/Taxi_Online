package maktabsharif.data.domain;

import maktabsharif.data.domain.enums.VehicleType;

public abstract class Vehicle {
    private String brand;
    private String model;
    private String colour;
    private Driver driver;
    private double location_lat;
    private double location_lon;
    private VehicleType vehicleType;
    private String driver_userName;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, String colour, Driver driver, double location_lat, double location_lon, VehicleType vehicleType) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.driver = driver;
        this.location_lat = location_lat;
        this.location_lon = location_lon;
        this.vehicleType = vehicleType;
    }

    public double getLocation_lat() {
        return location_lat;
    }

    public void setLocation_lat(double location_lat) {
        this.location_lat = location_lat;
    }

    public double getLocation_lon() {
        return location_lon;
    }

    public void setLocation_lon(double location_lon) {
        this.location_lon = location_lon;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getDriver_userName() {
        return driver_userName;
    }

    public void setDriver_userName(String driver_userName) {
        this.driver_userName = driver_userName;
    }
}
