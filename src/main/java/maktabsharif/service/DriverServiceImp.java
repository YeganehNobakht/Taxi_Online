package maktabsharif.service;

import maktabsharif.data.domain.Car;
import maktabsharif.data.domain.Driver;
import maktabsharif.data.domain.Passenger;
import maktabsharif.data.domain.Travel;
import maktabsharif.data.domain.enums.DriverTripState;
import maktabsharif.data.domain.enums.TravelPaying;
import maktabsharif.data.repository.*;
import maktabsharif.service.exceptions.checkes.InvalidEmailAddressException;
import maktabsharif.service.validation.Validations;
import org.springframework.stereotype.Service;

import java.util.Scanner;
@Service
public class DriverServiceImp implements DriverService {
    private final Scanner scanner;
    private final Validations validations;
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;
    private final TravelRepository travelRepository;
    private final PassengerRepository passengerRepository;



    public DriverServiceImp(Scanner scanner, Validations validations, DriverRepository driverRepository,
                            CarRepository carRepository, TravelRepository travelRepository,
                            PassengerRepository passengerRepository) {
        this.scanner = scanner;
        this.validations = validations;
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.travelRepository = travelRepository;
        this.passengerRepository = passengerRepository;
    }

    @Override
    public void addGroupDriver() throws InvalidEmailAddressException {
        boolean validDriverInput = false;
        int driverNumber=0;
        //TODO
        while (!validDriverInput) {
            try {
                System.out.println("Enter number of drivers");
                String strDriverNumber = scanner.next();
                driverNumber = Integer.parseInt(strDriverNumber);
                validDriverInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input type! try again...");
            }
        }
        System.out.println("Enter drivers information: (username, name, last name, email, national code,car)");
        for (int i=0 ; i<driverNumber ; i++) {
            Driver driver = new Driver(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next());
            //TAXI_COMPANY.addDriver(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next());
            if (validations.validate(driver.getEmail(), driver.getNationalId() , driver.getName(), driver.getLastName())) {
                driverRepository.saveDriver(driver);
            }
            System.out.println("Enter vehicle information for driver with username "+ driver.getUserName());
            System.out.println("car serial,  brand, model, colour,  location_lat,      location_lon");
            Car car = new Car(scanner.next(),scanner.next(),scanner.next(),scanner.next(),scanner.nextDouble(),scanner.nextDouble(),driver.getUserName());
            carRepository.saveCar(car);

        }

    }
    @Override
    public void driverSignupAndLogin() throws InvalidEmailAddressException {
        System.out.println("Username");
        String username = scanner.next();
        // determine if exist username
        Driver driver = existInDriver(username);
        Travel travel = travelRepository.findTravelByDriverUsername(driver.getUserName());
        if (driver != null) {
            if (travel!=null){
                System.out.println(driver.getName() + " " + driver.getLastName() + " " + driver.getUserName());
                System.out.println("1.Confirm cash receipt \n2.Travel finished \n3.Exit");
                String driverHomeScreen = scanner.next();
                switch (driverHomeScreen) {
                    case "1":
                        if (driver.getDriverTripState().equals("WAITING")) {
                            if (travel.getTravelPaying().equals(TravelPaying.PAYING_CASH)) {
                                driver.setConfirmCashReceipt(true);
                                String driverIsTravelFinished = scanner.next();
                                if (driverIsTravelFinished.equals("2")) {
                                    changeTripStatus(driver,travel);
                                }
                                String exit = scanner.next();
                            } else {
                                System.out.println("Passenger choose to pay online");
                            }
                        } else {
                            System.out.println("You are not on a trip");
                        }
                        break;

                    case "2":
                        if (driver.getDriverTripState().equals("WAITING")) {
                            changeTripStatus(driver,travel);
                        } else {
                            System.out.println("You are not on a trip");
                        }
                        break;
                    case "3":
                        break;
                }
            }
        }
        else {
            registerDriver();
        }
    }

    @Override
    public  Driver existInDriver (String username){
        return driverRepository.findDriverById(username);
    }


    @Override
    public void registerDriver() throws InvalidEmailAddressException {
        int driverRegistrationItem;
        do {
            System.out.println("1.Register \n2.Exit");
            String driverRegistrationOption = scanner.next();
            //TODO function
            driverRegistrationItem = validations.validIntInput(driverRegistrationOption);
            if (driverRegistrationItem == 1) {
                System.out.println("Enter drivers information: (username, name, last name, email, national code,car)");
                Driver driver = new Driver(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next());
                if (validations.validate(driver.getEmail(), driver.getNationalId() , driver.getName(), driver.getLastName())) {
                    driverRepository.saveDriver(driver);
                }
            }

        }while (driverRegistrationItem!=2);
    }
    @Override
    public void changeTripStatus(Driver driver,Travel travel){
        driver.setDriverTripState(DriverTripState.READY_FOR_TRIP);
        driverRepository.updateDriver(driver);
        Passenger passenger = passengerRepository.findPassengerById(travel.getPassenger_userName());
        passenger.setPassengerTripState("READY_FOR_TRIP");
        passengerRepository.updatePassenger(passenger);
        Car car = carRepository.findCarByDriverUsername(driver.getUserName());
        car.setLocation_lat(travel.getDestination_lat());
        car.setLocation_lon(travel.getDestination_lon());
        carRepository.updateCarLocation(car);
    }
}
