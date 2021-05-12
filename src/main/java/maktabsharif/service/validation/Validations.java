
package maktabsharif.service.validation;


import maktabsharif.service.exceptions.checkes.InvalidEmailAddressException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class Validations {


    public boolean validate(String email, String nationalID, String name, String lastName) throws InvalidEmailAddressException {
        return validateEmail(email) && validateNationalId(nationalID) && validateName(name) && validateLastName(lastName);
    }

    private boolean validateName(String name) {
        return validateByRegex("name", name);
    }

    private boolean validateLastName(String lastName) {
        return validateByRegex("lastName", lastName);
    }

    private boolean validateEmail(String email) throws InvalidEmailAddressException {
        return isDomainAndFormatValid(email) && validateByRegex("email", email);
    }

    private boolean isDomainAndFormatValid(String email) throws InvalidEmailAddressException {
        String[] validDomains = {"gmail.com", "yahoo.com", "outlook.com", "hotmail.com"};
        for (String validDomain : validDomains) {
            if (validDomain.equals(email.substring(email.indexOf("@") + 1))) {
                return true;
            }
        }
        throw new InvalidEmailAddressException("Domain is invalid.");
    }

    private boolean validateNationalId(String nationalId) {
        return validateByRegex("nationalId", nationalId);
    }
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_NATIONAL_ID_REGEX =
            Pattern.compile("^\\d{10}$");

    private static final Pattern VALID_Name_REGEX =
            Pattern.compile("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");


    public static boolean validateByRegex(String wantToMatch, String input) {
        if (wantToMatch.equalsIgnoreCase("email")) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(input);
            if(matcher.find())
                return true;
            else
                throw new IllegalStateException("check input");
        }
        if (wantToMatch.equalsIgnoreCase("NationalId")) {
            Matcher matcher = VALID_NATIONAL_ID_REGEX.matcher(input);
            if(matcher.find())
                return true;
            else
                throw new IllegalStateException("check input");
            //matchFound(match);
        }
        if (wantToMatch.equalsIgnoreCase("Name") || wantToMatch.equalsIgnoreCase("lastName")) {
            Matcher matcher = VALID_Name_REGEX.matcher(input);
            if(matcher.find())
                return true;
            else
                throw new IllegalStateException("check input");
        }
        return false;
    }
    public int validIntInput(String input){
        try {
            int inpt = Integer.parseInt(input);
            return inpt;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid input format");
        }
    }

}