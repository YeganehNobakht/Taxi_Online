package maktabsharif.data.repository;

import maktabsharif.data.domain.Driver;

public interface DriverRepository {
    void saveDriver(Driver driver);
    Driver findDriverById(String id);
    int  updateDriver(Driver driver);
    String findDriverFromCarLocation(double lat,double lon);
    Driver findDriverByIdAndTripState(String id,String tripState);

}
