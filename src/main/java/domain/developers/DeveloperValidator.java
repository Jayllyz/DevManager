package domain.developers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeveloperValidator {
    public boolean validateFirstName(String firstName) {
        if(firstName == null) {
            throw new IllegalArgumentException("first name is not defined");
        }
        if(firstName.length() > 30){
            throw new IllegalArgumentException("first name can't be more than 30 characters");
        }
        if(firstName.length() < 3){
            throw new IllegalArgumentException("first name can't be less than 3 characters");
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]*$");
        Matcher matcher = pattern.matcher(firstName);

        if(matcher.matches()) {
            throw new IllegalArgumentException("first name can't contain special characters");
        }

        return true;
    }
}
