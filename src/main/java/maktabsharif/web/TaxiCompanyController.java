package maktabsharif.web;

import maktabsharif.service.TaxiCompanyService;
import maktabsharif.service.TaxiCompanyServiceImp;
import maktabsharif.service.exceptions.checkes.InvalidEmailAddressException;
import org.springframework.stereotype.Controller;

import java.util.Scanner;


@Controller
public class TaxiCompanyController {
    private final TaxiCompanyService taxiCompanyService;
    private final Scanner scanner;

    public TaxiCompanyController(TaxiCompanyService taxiCompanyService, Scanner scanner) {
        this.taxiCompanyService = taxiCompanyService;
        this.scanner = scanner;
    }


    public void RanApplication() throws InvalidEmailAddressException {

        taxiCompanyService.RunApplication(scanner);
    }
}
