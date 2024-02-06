package shared.developers;

import shared.exceptions.InvalidAttributeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Name {
    public static final String NO_SPECIAL_CHARACTERS_REGEX = "^[a-zA-Z]+$";
    private final String name;

    public Name(String name) throws InvalidAttributeException {

        if(name == null) {
            throw new InvalidAttributeException("Developer name is not defined");
        }
        if(name.length() > 30){
            throw new InvalidAttributeException("Developer name can't be more than 30 characters");
        }
        if(name.length() < 2){
            throw new InvalidAttributeException("Developer name can't be less than 2 characters");
        }

        Pattern pattern = Pattern.compile(NO_SPECIAL_CHARACTERS_REGEX);
        Matcher matcher = pattern.matcher(name);

        if(!matcher.matches()) {
            throw new InvalidAttributeException("Developer name can't contain special characters");
        }

        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
