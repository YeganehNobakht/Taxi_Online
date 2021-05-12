package maktabsharif;/*
package ir.maktabshatif;
import ir.maktabshatif.data.model.Car;
import ir.maktabshatif.data.model.Driver;
import ir.maktabshatif.driver.DriverDao;
import ir.maktabshatif.data.domain.enums.DriverTripState;
import ir.maktabshatif.data.domain.enums.PassengerTripState;
import ir.maktabshatif.data.domain.enums.VehicleType;
import ir.maktabshatif.service.exceptions.unchecked.InvalidInputRangeException;
import ir.maktabshatif.data.model.Passenger;
import ir.maktabshatif.data.model.Travel;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Travel travel;
    private static final TaxiCompany TAXI_COMPANY = new TaxiCompany();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1.Add a group of drivers \n" +
                    "2.Add a group of passengers \n" +
                    "3.Driver signup or login \n" +
                    "4.Passenger signup or login \n" +
                    "5.Show ongoing travels \n" +
                    "6.Show a list of drivers\n" +
                    "7.Show a list of passengers");

            boolean validInput = false;

            while (!validInput) {
                int instructionItem=0;
                try {
                    String instruction = scanner.next();
                    instructionItem = Integer.parseInt(instruction);
                    if (instructionItem<1 || instructionItem>7)
                        throw new InvalidInputRangeException("Out of legal input range");
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong input type! try again...");
                }catch (InvalidInputRangeException exception){
                    System.out.println(exception.getMessage());
                    System.out.println("Try again");
                }
                switch (instructionItem) {
                    case 1:
                        addGroupDriver();
                        break;
                    case 2:
                        addGroupPassenger();
                        break;
                    case 3:
                        driverSignupAndLogin();
                        break;
                    case 4:
                        passengerSignupAndLogin();
                        break;
                    case 5:
                        TAXI_COMPANY.showTravelInfo();
                        break;
                    case 6:
                        System.out.println(TAXI_COMPANY.toStringDriver());
                        break;
                    case 7:
                        System.out.println(TAXI_COMPANY.toString());
                        break;
                    case 8:
                        break;
                }

            }//scanner.hasNext();
        }
    }
    public static void addGroupDriver(){
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
            TAXI_COMPANY.addDriver(driver);
            System.out.println("Enter vehicle information for driver with username "+ driver.getUserName());
            System.out.println("brand, model, colour,  driver,  location_lat, location_lon");
            Car car = new Car(scanner.next(),scanner.next(),scanner.next(),driver,scanner.nextDouble(),scanner.nextDouble(), VehicleType.valueOf(scanner.next().toUpperCase()));
            TAXI_COMPANY.addCar(car,driver);

        }

    }
    public static void addGroupPassenger(){
        boolean validPassengerInput = false;
        int PassengerNumber=0;
        while (!validPassengerInput) {
            try {
                System.out.println("Enter number of passengers");
                String strPassengerNumber = scanner.next();
                PassengerNumber = Integer.parseInt(strPassengerNumber);
                validPassengerInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input type! try again...");
            }
        }
        System.out.println("Enter passengers information: (username, name, last name, email, national code)");
        for (int i=0 ; i<PassengerNumber; i++) {
            Passenger passenger = new Passenger(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next());
            TAXI_COMPANY.addPassenger(passenger);
        }
    }
    public static void driverSignupAndLogin(){
        System.out.println("Username");
        String username = scanner.next();
        // determine if exist username
        Driver driver = existInDriver(username);
        if (driver!=null){
            System.out.println(driver.getName() + " " + driver.getLastName() + " " + driver.getUserName());
            System.out.println("1.Confirm cash receipt \n2.Travel finished \n3.Exit");
            String driverHomeScreen = scanner.next();
            switch (driverHomeScreen){
                case "1":
                    for (Travel travel : TAXI_COMPANY.getTravelList()){
                        if (travel.getDriver().equals(driver)){
                            if (travel.getPassenger().payCash()){
                                driver.setConfirmCashReceipt(true);
                                String driverIsTravelFinished = scanner.next();
                                if (driverIsTravelFinished.equals("2")){
                                    driver.isTravelFinish();
                                    travel.getPassenger().setPassengerTripState(PassengerTripState.READY_FOR_TRIP);
                                    travel.getDriver().setDriverTripState(DriverTripState.READY_FOR_TRIP);
                                    travel.getDriver().getVehicle().setLocation_lat(travel.getDestination_lat());
                                    travel.getDriver().getVehicle().setLocation_lon(travel.getDestination_lon());
                                }
                                String exit = scanner.next();
                            }
                            else {
                                System.out.println("Passenger choose to pay online");
                            }
                            break;
                        }
                    }

                case "2":
                    if (driver.getDriverTripState().equals(DriverTripState.WAITING)) {
                        driver.isTravelFinish();
                        travel.getPassenger().setPassengerTripState(PassengerTripState.READY_FOR_TRIP);
                        driver.setDriverTripState(DriverTripState.READY_FOR_TRIP);
                        driver.getVehicle().setLocation_lat(travel.getDestination_lat());
                        driver.getVehicle().setLocation_lon(travel.getDestination_lon());
                    }else{
                        System.out.println("You are not on a trip");
                    }
                    break;
                case "3":
                    break;
            }
        }
        else {
            registerDriver();
        }
    }
    public static Driver existInDriver (String username){
        return DriverDao.getDriverByUsername(username);
    }

    public static void registerDriver(){
        int driverRegistrationItem = 0;
        do {
            System.out.println("1.Register \n2.Exit");
            String driverRegistrationOption = scanner.next();
            //TODO function
            try {
                driverRegistrationItem = Integer.parseInt(driverRegistrationOption);
            } catch (NumberFormatException e) {
                System.out.println("Wrong input type! try again...");
            }
            if (Integer.parseInt(driverRegistrationOption) == 1) {
                System.out.println("Enter drivers information: (username, name, last name, email, national code,car)");
                Driver driver = new Driver(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next());
                TAXI_COMPANY.addDriver(driver);
            }

        }while (driverRegistrationItem!=2);
    }
    public static Passenger existInPassenger (String username){
        Passenger foundedPassenger = null;
        for (Passenger passenger: TAXI_COMPANY.getPassengerList()) {
            if (passenger.getUserName().equals(username)) {
                foundedPassenger = passenger;
                break;
            }
        }
        return foundedPassenger;
    }

    public static void passengerSignupAndLogin(){
        System.out.println("Username");
        String passengerUsername = scanner.next();
        //System.out.println(TAXI_COMPANY.getPassengerList());
        Passenger passenger =existInPassenger(passengerUsername);
        if (passenger!=null){
            System.out.println(passenger.getName() + " " + passenger.getLastName() + " " + passenger.getUserName());
            System.out.println("1.Travel request (pay by cash) \n2.Travel request (pay by account balance) \n3.Increase account balance \n4.Exit");
            int passengerHomeScreen=0 ;
            boolean error = false;
            while(!error) {
                try {
                    passengerHomeScreen = scanner.nextInt();
                    error = true;
                }catch (NumberFormatException e){
                    System.out.println("Wrong input type! try again...");
                }
            }
            switch (passengerHomeScreen){
                case 1:
                    travelRequestPayByCash(passenger);
                    break;
                case 2:
                    travelRequestPayByAccountBalance(passenger);
                    break;
                case 3:
                    System.out.println("How much do you want to increase?");
                    double increment = scanner.nextDouble();
                    double currentAccountBalance = passenger.getAccountBalance();
                    passenger.setAccountBalance(currentAccountBalance+increment);
                    break;
                case 4:
                    break;
            }
        } else {
            registerPassenger();
        }
        // }
    }
    public static Driver findDriverForTrip(Travel travel){
        for (Driver driver : TAXI_COMPANY.getDriverList()) {
            if (driver.getVehicle().getLocation_lat() - travel.getOrigin_lat() < 1000 && driver.getVehicle().getLocation_lon() - travel.getOrigin_lon() < 1000 && driver.getDriverTripState() == DriverTripState.READY_FOR_TRIP) {
                travel.setDriver(driver);
                driver.setDriverTripState(DriverTripState.WAITING);
                travel.calculateTripPrice();
                System.out.println("Your request accepted by: " + driver.getName() + " " + driver.getLastName()
                + " "+"The travel price is: "+travel.getPrice() +" Toman");
                return driver;
            }
        }
        return null;
    }
    public static void travelRequestPayByCash(Passenger passenger){
        if (passenger.getPassengerTripState().equals(PassengerTripState.ON_TRIP)){
            System.out.println("You are on another trip");
        }
        else {
            System.out.println("Enter the origin and destination of your travel:");
            travel = new Travel(passenger, scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble(),"PayingCash");
            if (findDriverForTrip(travel)!=null){
                passenger.setPassengerTripState(PassengerTripState.ON_TRIP);
                */
/** add travel to travelList*//*

                TAXI_COMPANY.getTravelList().add(travel);
                System.out.println("1.Increase account balance \n2.Exit");
                String afterReserveCarOption = scanner.next();
                switch (afterReserveCarOption) {
                    case "1":
                        System.out.println("How much do you want to increase?");
                        double increment = scanner.nextDouble();
                        double currentAccountBalance = passenger.getAccountBalance();
                        passenger.setAccountBalance(currentAccountBalance + increment);
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

    public static void travelRequestPayByAccountBalance(Passenger passenger){
        if (passenger.getPassengerTripState().equals(PassengerTripState.ON_TRIP)){
            System.out.println("You are on another trip");
        }
        else {
            System.out.println("Enter the origin and destination of your travel:");
            travel = new Travel(passenger, scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
            if (passenger.getAccountBalance()>travel.calculateTripPrice()){
            Driver driver = findDriverForTrip(travel);
            if (driver!=null){
                passenger.setPassengerTripState(PassengerTripState.ON_TRIP);
                */
/** add travel to travelList*//*

                TAXI_COMPANY.getTravelList().add(travel);
                */
/*if (passenger.getAccountBalance() < travel.getPrice()) {
                    System.out.println("Please increase your balance");
                } else {*//*

                    double currentBalance = passenger.getAccountBalance();
                    passenger.setAccountBalance(currentBalance - travel.getPrice());
                    passenger.setPassengerTripState(PassengerTripState.READY_FOR_TRIP);
                    driver.setDriverTripState(DriverTripState.READY_FOR_TRIP);
                }else {
                System.out.println("There is not any car near you");
                }
            }else {
                System.out.println("Your account balance is not enough, Please recharge it.");
            }
        }
    }

    public static void registerPassenger (){
        int passengerRegistrationItem = 0;
        do {
            System.out.println("1.Register \n2.Exit");
            String passengerRegistrationOption = scanner.next();

            try {
                passengerRegistrationItem = Integer.parseInt(passengerRegistrationOption);
            } catch (NumberFormatException e) {
                System.out.println("Wrong input type! try again...");
            }
            if (Integer.parseInt(passengerRegistrationOption) == 1) {
                System.out.println("Enter drivers information: (username, name, last name, email, national code,car)");
                Passenger passenger = new Passenger(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next());
                TAXI_COMPANY.addPassenger(passenger);
            }
        } while (passengerRegistrationItem != 2);
    }
}
*/
