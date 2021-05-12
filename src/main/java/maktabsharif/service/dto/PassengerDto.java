package maktabsharif.service.dto;

import maktabsharif.data.domain.User;
import maktabsharif.data.domain.enums.PassengerTripState;


public class PassengerDto extends User {
    private double accountBalance;
    private String passengerTripState;
    private PassengerTripState tripState;
    private boolean payingCash = false;

    public PassengerDto(String userName, String name, String lastName, String email, String nationalId) {
        super(userName, name, lastName, email, nationalId);
        setPassengerTripState("READY_FOR_TRIP");
    }


    public PassengerDto() {
    }

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
