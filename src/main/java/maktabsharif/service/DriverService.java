package maktabsharif.service;

import maktabsharif.data.domain.Driver;
import maktabsharif.data.domain.Travel;
import maktabsharif.service.exceptions.checkes.InvalidEmailAddressException;

public interface DriverService {
    void addGroupDriver() throws InvalidEmailAddressException;
    void driverSignupAndLogin() throws InvalidEmailAddressException;
    Driver existInDriver (String username);
    void registerDriver() throws InvalidEmailAddressException;
    void changeTripStatus(Driver driver, Travel travel);
}
