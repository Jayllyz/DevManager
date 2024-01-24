package domain.projects.attributes;

public class Description {
    private String description;

    public Description(String description) {
        if(description == null) {
            throw new IllegalArgumentException("Project description is not defined");
        }

        if(description.length() > 500){
            throw new IllegalArgumentException("Project description can't be more than 500 characters");
        }

        this.description = description;
    }
}
