package maktabsharif.data.repository;

import maktabsharif.data.domain.Passenger;

public interface PassengerRepository {
    void savePassenger(Passenger passenger) ;
    Passenger findPassengerById(String id);
    int  updatePassenger(Passenger passenger);
}
