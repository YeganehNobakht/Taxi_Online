package maktabsharif.data.domain;


import javax.persistence.*;

@Entity
public class Car  {
    @Id
    private String Id;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private String colour;
    @Column
    private double location_lat;
    @Column
    private double location_lon;

    @Column
    private String driver_userName;

    /*public Car(String brand, String model, String colour, Driver driver, double location_lat, double location_lon, VehicleType vehicleType) {
        super(brand, model, colour, driver, location_lat, location_lon, vehicleType);
        setVehicleType(VehicleType.CAR);
    }
*/

    public Car(String id, String brand, String model, String colour, double location_lat, double location_lon, String driver_userName) {
        Id = id;
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.location_lat = location_lat;
        this.location_lon = location_lon;
        this.driver_userName = driver_userName;
    }

    public Car() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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



    public String getDriver_userName() {
        return driver_userName;
    }


    public void setDriver_userName(String driver_userName) {
        this.driver_userName = driver_userName;
    }
}

