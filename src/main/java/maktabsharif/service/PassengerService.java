package maktabsharif.service;

import maktabsharif.data.domain.Passenger;
import maktabsharif.service.exceptions.checkes.InvalidEmailAddressException;

public interface PassengerService {

    void addGroupPassenger() throws InvalidEmailAddressException;
    Passenger existInPassenger (String username);
    void passengerSignUpAndLogin() throws InvalidEmailAddressException;
    void registerPassenger () throws InvalidEmailAddressException;
}
