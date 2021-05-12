package maktabsharif.data.repository;

import maktabsharif.data.domain.Car;

public interface CarRepository {
     void saveCar(Car car);
     int  updateCarLocation(Car car);
     Car findCarByDriverUsername(String id);

}
