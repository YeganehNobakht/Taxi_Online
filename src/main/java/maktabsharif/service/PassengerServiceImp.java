package maktabsharif.service;

import maktabsharif.data.domain.Passenger;
import maktabsharif.data.repository.PassengerRepository;
import maktabsharif.service.exceptions.checkes.InvalidEmailAddressException;
import maktabsharif.service.validation.Validations;
import org.springframework.stereotype.Service;

import java.util.Scanner;
@Service
public class PassengerServiceImp implements PassengerService {
    private final Scanner scanner;
    private final PassengerRepository passengerRepository;
    private final TravelService travelService;
    private final Validations validations;

    public PassengerServiceImp(Scanner scanner, PassengerRepository passengerRepository,
                               TravelService travelService, Validations validations) {
        this.scanner = scanner;
        this.passengerRepository = passengerRepository;
        this.travelService = travelService;
        this.validations = validations;
    }

    @Override
    public void addGroupPassenger() throws InvalidEmailAddressException {
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
            if (validations.validate(passenger.getEmail(), passenger.getNationalId(), passenger.getName(), passenger.getLastName())) {
                passengerRepository.savePassenger(passenger);
            }
        }
    }
    @Override
    public Passenger existInPassenger (String username) {
        return passengerRepository.findPassengerById(username);
    }
    @Override
    public void passengerSignUpAndLogin() throws InvalidEmailAddressException {
        System.out.println("Username");
        Passenger passenger =existInPassenger(scanner.next());
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
                    //TODO Passenger -> PassengerDto
                    travelService.travelRequestPayByCash(passenger);
                    break;
                case 2:
                    travelService.travelRequestPayByAccountBalance(passenger);
                    break;
                case 3:
                    System.out.println("How much do you want to increase?");
                    double increment = scanner.nextDouble();
                    double oldBalance = passenger.getAccountBalance();
                    double newBalance = oldBalance + increment;
                    passenger.setAccountBalance(newBalance);
                    passengerRepository.updatePassenger(passenger);
                    break;
                case 4:
                    break;
            }
        } else {
            registerPassenger();
        }
    }

    @Override
    public void registerPassenger () throws InvalidEmailAddressException {
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
                if (validations.validate(passenger.getEmail(), passenger.getNationalId(), passenger.getName(), passenger.getLastName())) {
                    passengerRepository.savePassenger(passenger);
                }
            }
        } while (passengerRegistrationItem != 2);
    }
}
