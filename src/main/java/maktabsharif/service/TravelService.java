package maktabsharif.service;

import maktabsharif.data.domain.Driver;
import maktabsharif.data.domain.Passenger;
import maktabsharif.data.domain.Travel;

public interface TravelService {

    Driver findDriverForTrip(Travel travel);
    void travelRequestPayByCash(Passenger passenger);
    void travelRequestPayByAccountBalance(Passenger passenger);
}
