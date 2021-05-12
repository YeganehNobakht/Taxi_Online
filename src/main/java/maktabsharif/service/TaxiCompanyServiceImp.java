package maktabsharif.service;

import maktabsharif.service.exceptions.checkes.InvalidEmailAddressException;
import maktabsharif.service.exceptions.unchecked.InvalidInputRangeException;
import org.springframework.stereotype.Service;

import java.util.Scanner;
@Service
public class TaxiCompanyServiceImp implements TaxiCompanyService {


    private final PassengerService passengerService;
    private final DriverService driverService;

    public TaxiCompanyServiceImp(PassengerService passengerService, DriverService driverService) {
        this.passengerService = passengerService;
        this.driverService = driverService;
    }

    @Override
    public void RunApplication(Scanner scanner) throws InvalidEmailAddressException {
        while (true) {
            System.out.println("1.Add a group of drivers \n" +
                    "2.Add a group of passengers \n" +
                    "3.Driver signup or login \n" +
                    "4.Passenger signup or login \n" +
                    "5.Show ongoing travels \n" +
                    "6.Show a list of drivers\n" +
                    "7.Show a list of passengers");

            boolean appInput = false;

            while (!appInput) {
                //TODO::method get instruction
                int instructionItem = 0;
                try {
                    String instruction = scanner.next();
                    instructionItem = Integer.parseInt(instruction);
                    if (instructionItem < 1 || instructionItem > 7)
                        throw new InvalidInputRangeException("Out of legal input range");
                    appInput = true;
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Wrong input type! try again...");
                } catch (InvalidInputRangeException exception) {
                    throw new InvalidInputRangeException("Invalid input");
                }
                //TODO :: method
                switch (instructionItem) {
                    case 1:
                        driverService.addGroupDriver();
                        break;
                    case 2:
                        passengerService.addGroupPassenger();
                        break;
                    case 3:
                        driverService.driverSignupAndLogin();
                        break;
                    case 4:
                        passengerService.passengerSignUpAndLogin();
                        break;
                    case 5:
                        //DBTablePrinter.printTravel();
                        break;
                    case 6:
                        //DBTablePrinter.printDriverOrPassenger("driver");
                        break;
                    case 7:
                        //DBTablePrinter.printDriverOrPassenger("passenger");
                        break;
                    case 8:
                        break;
                }

            }//scanner.hasNext();
        }
    }
    @Override
    public boolean appInputValidate(boolean appInput, Scanner scanner){
        return appInput;
    }
}
