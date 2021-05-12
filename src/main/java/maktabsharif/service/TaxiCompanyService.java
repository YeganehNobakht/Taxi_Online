package maktabsharif.service;

import maktabsharif.service.exceptions.checkes.InvalidEmailAddressException;

import java.util.Scanner;

public interface TaxiCompanyService {

    void RunApplication(Scanner scanner) throws InvalidEmailAddressException;
    boolean appInputValidate(boolean appInput, Scanner scanner);
}
