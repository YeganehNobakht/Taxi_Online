package maktabsharif.data.domain;

import maktabsharif.data.domain.enums.TravelPaying;


import javax.persistence.GenerationType;
import javax.persistence.*;
@Entity
public class Travel {
    //private Car car;
    //private Driver driver;
    //private Passenger passenger;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int travelId;
    @Column
    private double origin_lat;
    @Column
    private double origin_lon;
    @Column
    private double destination_lat;
    @Column
    private double destination_lon;
    @Column
    private double price;
    @Column
    private String Passenger_userName;
    @Column
    private String Driver_userName;
    @Column
    private TravelPaying travelPaying;
    //private TravelState travelState;


    public Travel( double origin_lat, double origin_lon, double destination_lat, double destination_lon,TravelPaying travelPay) {
        this.origin_lat = origin_lat;
        this.origin_lon = origin_lon;
        this.destination_lat = destination_lat;
        this.destination_lon = destination_lon;
        this.travelPaying = travelPay;
    }

    public Travel(double origin_lat, double origin_lon, double destination_lat, double destination_lon, double price, String passenger_userName, String driver_userName, TravelPaying travelPaying) {
        this.origin_lat = origin_lat;
        this.origin_lon = origin_lon;
        this.destination_lat = destination_lat;
        this.destination_lon = destination_lon;
        this.price = price;
        Passenger_userName = passenger_userName;
        Driver_userName = driver_userName;
        this.travelPaying = travelPaying;
    }

    public Travel() {

    }

    public String getPassenger_userName() {
        return Passenger_userName;
    }

    public void setPassenger_userName(String passenger_userName) {
        Passenger_userName = passenger_userName;
    }

    public String getDriver_userName() {
        return Driver_userName;
    }

    public void setDriver_userName(String driver_userName) {
        Driver_userName = driver_userName;
    }

    public double getOrigin_lat() {
        return origin_lat;
    }

    public void setOrigin_lat(double origin_lat) {
        this.origin_lat = origin_lat;
    }

    public double getOrigin_lon() {
        return origin_lon;
    }

    public void setOrigin_lon(double origin_lon) {
        this.origin_lon = origin_lon;
    }

    public double getDestination_lat() {
        return destination_lat;
    }

    public void setDestination_lat(double destination_lat) {
        this.destination_lat = destination_lat;
    }

    public double getDestination_lon() {
        return destination_lon;
    }

    public void setDestination_lon(double destination_lon) {
        this.destination_lon = destination_lon;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TravelPaying getTravelPaying() {
        return travelPaying;
    }

    public void setTravelPaying(TravelPaying travelPaying) {
        this.travelPaying = travelPaying;
    }

    public double calculateTripPrice(){
        this.price=distance(getOrigin_lat(),getDestination_lat(),getOrigin_lon(),getDestination_lon(),0,0)*1000;
        return this.price;
    }

    public static double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {
        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        double height = el1 - el2;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }
}
