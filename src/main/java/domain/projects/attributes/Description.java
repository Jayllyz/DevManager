package domain.projects.attributes;

import shared.exceptions.InvalidAttributeException;

public class Description {
    private String description;

    public Description(String description) throws InvalidAttributeException {
        if(description == null) {
            throw new InvalidAttributeException("Project description is not defined");
        }

        if(description.length() > 500){
            throw new InvalidAttributeException("Project description can't be more than 500 characters");
        }

        this.description = description;
    }
}
