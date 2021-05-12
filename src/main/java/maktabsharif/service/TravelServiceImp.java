package maktabsharif.service;

import maktabsharif.data.domain.Driver;
import maktabsharif.data.domain.Passenger;
import maktabsharif.data.domain.Travel;
import maktabsharif.data.domain.enums.DriverTripState;
import maktabsharif.data.domain.enums.TravelPaying;
import maktabsharif.data.repository.DriverRepository;
import maktabsharif.data.repository.PassengerRepository;
import maktabsharif.data.repository.TravelRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;
@Service
public class TravelServiceImp implements TravelService {
    private final Scanner scanner;
    private final TravelRepository travelRepository;
    private final DriverRepository driverRepository;
    private final PassengerRepository passengerRepository;

    public TravelServiceImp(Scanner scanner, TravelRepository travelRepository,
                            DriverRepository driverRepository, PassengerRepository passengerRepository) {
        this.scanner = scanner;
        this.travelRepository = travelRepository;
        this.driverRepository = driverRepository;
        this.passengerRepository = passengerRepository;
    }

    @Override
    public Driver findDriverForTrip(Travel travel){
        String driverUserName = driverRepository.findDriverFromCarLocation(travel.getOrigin_lat(),travel.getOrigin_lon());
        if (!driverUserName.equals(null)) {
            Driver driver = driverRepository.findDriverByIdAndTripState(driverUserName,"READY_FOR_TRIP");
            if (driver!=null) {
                driver.setDriverTripState(DriverTripState.WAITING);
                driverRepository.updateDriver(driver);
                travel.calculateTripPrice();
                System.out.println("Your request accepted by: " + driver.getName() + " " + driver.getLastName()
                        + " " + "The travel price is: " + travel.getPrice() + " Toman");
                return driver;
            }
            return null;
        }else {
            return null;
        }
    }
    @Override
    public void travelRequestPayByCash(Passenger passenger){
        if (passenger.getPassengerTripState().equalsIgnoreCase("ON_TRIP")){
            System.out.println("You are on another trip");
        }
        else {
            System.out.println("Enter the origin and destination of your travel:");
            Travel travel = new Travel(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble(), TravelPaying.PAYING_CASH);
            Driver driver = findDriverForTrip(travel);
            if (driver!=null){
                passenger.setPassengerTripState("ON_TRIP");
                passengerRepository.updatePassenger(passenger);
                /** add travel to travelList*/
                travelRepository.saveTravel(travel);
                travel.setPassenger_userName(passenger.getUserName());
                travel.setDriver_userName(driver.getUserName());
                travelRepository.updateTravel(travel);
                System.out.println("1.Increase account balance \n2.Exit");
                String afterReserveCarOption = scanner.next();
                switch (afterReserveCarOption) {
                    case "1":
                        System.out.println("How much do you want to increase?");
                        double increment = scanner.nextDouble();
                        double oldBalance = passenger.getAccountBalance();
                        double newBalance = oldBalance + increment;
                        passenger.setAccountBalance(newBalance);
                        passengerRepository.updatePassenger(passenger);
                        break;
                    case "2":
                        break;
                    default:
                        System.out.println("Wrong input");
                }
            } else {
                System.out.println("There is not any car near you");
            }
        }
    }
    @Override
    public void travelRequestPayByAccountBalance(Passenger passenger){
        if (passenger.getPassengerTripState().equals("ON_TRIP")){
            System.out.println("You are on another trip");
        }
        else {
            System.out.println("Enter the origin and destination of your travel:");
            Travel travel = new Travel(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble(), TravelPaying.PAYING_BY_ACCOUNT_BALANCE);
            if (passenger.getAccountBalance()>travel.calculateTripPrice()){
                Driver driver = findDriverForTrip(travel);
                if (driver!=null){
                    passenger.setPassengerTripState("ON_TRIP");
                    passengerRepository.updatePassenger(passenger);
                    /** add travel to travelList*/
                    travelRepository.saveTravel(travel);
                    travel.setPassenger_userName(passenger.getUserName());
                    travel.setDriver_userName(driver.getUserName());
                    travelRepository.updateTravel(travel);
                    double oldBalance = passenger.getAccountBalance();
                    double newBalance =oldBalance - travel.getPrice();
                    passenger.setAccountBalance(newBalance);
                    passengerRepository.updatePassenger(passenger);
                    passenger.setPassengerTripState("READY_FOR_TRIP");
                    passengerRepository.updatePassenger(passenger);
                    driver.setDriverTripState(DriverTripState.READY_FOR_TRIP);
                    driverRepository.updateDriver(driver);
                }
            }else {
                System.out.println("Your account balance is not enough, Please recharge it.");
            }
        }
    }
}
