package maktabsharif.service.dto;

import maktabsharif.data.domain.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Objects;

@Entity
public class DriverDto extends User {
/*    @Id
    private String userName;//national id
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column
    private String nationalId;*/
    @Transient
    private boolean confirmCashReceipt=false;
    @Column
    private String driverTripState ;
    @Column
    private String vehicle;


    public DriverDto(String userName, String name, String lastName, String email, String nationalId) {
        super(userName, name, lastName, email, nationalId);
        setDriverTripState("READY_FOR_TRIP");
    }

    /*public Driver(String userName, String name, String lastName, String email, String nationalId) {
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.nationalId = nationalId;
        setDriverTripState("READY_FOR_TRIP");

    }*/


    public DriverDto() {
    }
/*
    public DriverTripState getDriverTripState() {
        return driverTripState;
    }

    public void setDriverTripState(DriverTripState driverTripState) {
        this.driverTripState = driverTripState;
    }

    public boolean getConfirmCashReceipt() {
        return confirmCashReceipt;
    }

    public void setConfirmCashReceipt(boolean confirmCashReceipt) {
        this.confirmCashReceipt = confirmCashReceipt;
    }
    public void isTravelFinish(){
        this.driverTripState = DriverTripState.READY_FOR_TRIP;
    }

    public boolean isConfirmCashReceipt() {
        return confirmCashReceipt;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }*/


    public boolean isConfirmCashReceipt() {
        return confirmCashReceipt;
    }

    public void setConfirmCashReceipt(boolean confirmCashReceipt) {
        this.confirmCashReceipt = confirmCashReceipt;
    }

    public String getDriverTripState() {
        return driverTripState;
    }

    public void setDriverTripState(String driverTripState) {
        this.driverTripState = driverTripState;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverDto driver = (DriverDto) o;
        return confirmCashReceipt == driver.confirmCashReceipt && driverTripState == driver.driverTripState && Objects.equals(vehicle, driver.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmCashReceipt, driverTripState, vehicle);
    }
}

