package shared.projects;

import shared.exceptions.InvalidAttributeException;

public class Name {
    private String name;

    public Name(String name) throws InvalidAttributeException {
        if(name == null) {
            throw new InvalidAttributeException("Project name is not defined");
        }
        if(name.length() > 50){
            throw new InvalidAttributeException("Project name can't be more than 50 characters");
        }
        if(name.length() < 3){
            throw new InvalidAttributeException("Project name can't be less than 3 characters");
        }

        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
