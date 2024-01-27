package shared.developers;

import shared.exceptions.InvalidAttributeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    // look at part 10 of https://www.baeldung.com/java-email-validation-regex for the email regex validation rules
    public static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9+_-]+(\\.[A-Za-z0-9+_-]+)*@" + "[^-][A-Za-z0-9+-]+(\\.[A-Za-z0-9+-]+)*(\\.[A-Za-z]{2,})$";
    private final String email;

    public Email(String email) throws InvalidAttributeException {

        if(email == null) {
            throw new InvalidAttributeException("Developer email is not defined");
        }

        if(email.length() > 100){
            throw new InvalidAttributeException("Developer email can't be more than 100 characters");
        }

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches()) {
            throw new InvalidAttributeException("Developer email format is invalid");
        }

        this.email = email;
    }

    @Override
    public String toString() {
        return this.email;
    }
}
