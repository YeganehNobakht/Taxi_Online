package maktabsharif.data.domain;

import maktabsharif.data.domain.enums.PassengerTripState;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
@Entity
public class Passenger extends User {
    @Column
    private double accountBalance;
    @Column
    @ColumnDefault("'READY_FOR_TRIP'")
    private String passengerTripState;
    @Column(columnDefinition = "varchar(100) default 'READY_FOR_TRIP'")
    @Enumerated(EnumType.STRING)
    private PassengerTripState tripState;
    @Column
    private boolean payingCash = false;
   // private List<Travel> passengerTravels = new ArrayList<Travel>();

    public Passenger(String userName, String name, String lastName, String email, String nationalId) {
        super(userName, name, lastName, email, nationalId);
        setPassengerTripState("READY_FOR_TRIP");
    }

 /*   public Passenger(String userName, String name, String lastName, String email, String nationalId) {
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.nationalId = nationalId;
        setPassengerTripState("REDY_FOR_TRIP");
    }*/

    public Passenger() {
    }

    /*public PassengerTripState getPassengerTripState() {
        return passengerTripState;
    }

    public void setPassengerTripState(PassengerTripState passengerTripState) {
        this.passengerTripState = passengerTripState;
    }

    public boolean isPayingCash() {
        return payingCash;
    }

    public void setPayingCash(boolean payingCash) {
        this.payingCash = payingCash;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public boolean payCash(){
        this.payingCash =  true;
        return true;
    }*/


    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getPassengerTripState() {
        return passengerTripState;
    }

    public void setPassengerTripState(String passengerTripState) {
        this.passengerTripState = passengerTripState;
    }

    public boolean isPayingCash() {
        return payingCash;
    }

    public void setPayingCash(boolean payingCash) {
        this.payingCash = payingCash;
    }
}
