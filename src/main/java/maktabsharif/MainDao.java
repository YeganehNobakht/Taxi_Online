package maktabsharif;


import maktabsharif.configuration.Config;
import maktabsharif.service.exceptions.checkes.InvalidEmailAddressException;
import maktabsharif.web.TaxiCompanyController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainDao {


    public static void main(String[] args) {

        ApplicationContext iocContainer = new AnnotationConfigApplicationContext(Config.class);
        TaxiCompanyController taxiCompanyController = iocContainer.getBean(TaxiCompanyController.class);
        try {
            taxiCompanyController.RanApplication();
        } catch (InvalidEmailAddressException e) {
            e.printStackTrace();
        }
    }











}
