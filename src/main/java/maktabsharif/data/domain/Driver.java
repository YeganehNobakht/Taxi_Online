package maktabsharif.data.domain;

import maktabsharif.data.domain.enums.DriverTripState;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Driver extends User {
    @Transient
    private boolean confirmCashReceipt=false;
    @Column(columnDefinition = "varchar(100) default 'READY_FOR_TRIP'")
    @Enumerated(EnumType.STRING)
    private DriverTripState driverTripState ;
    @Column
    private String vehicle;


    public Driver(String userName, String name, String lastName, String email, String nationalId) {
        super(userName, name, lastName, email, nationalId);
        setDriverTripState(DriverTripState.READY_FOR_TRIP);
    }

    public Driver() {
    }

    public boolean isConfirmCashReceipt() {
        return confirmCashReceipt;
    }

    public void setConfirmCashReceipt(boolean confirmCashReceipt) {
        this.confirmCashReceipt = confirmCashReceipt;
    }

    public DriverTripState getDriverTripState() {
        return driverTripState;
    }

    public void setDriverTripState(DriverTripState driverTripState) {
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
        Driver driver = (Driver) o;
        return confirmCashReceipt == driver.confirmCashReceipt && driverTripState == driver.driverTripState && Objects.equals(vehicle, driver.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmCashReceipt, driverTripState, vehicle);
    }
}

