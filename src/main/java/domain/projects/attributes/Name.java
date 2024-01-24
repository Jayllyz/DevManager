package domain.projects.attributes;

public class Name {
    private String name;

    public Name(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Project name is not defined");
        }
        if(name.length() > 50){
            throw new IllegalArgumentException("Project name can't be more than 50 characters");
        }
        if(name.length() < 3){
            throw new IllegalArgumentException("Project name can't be less than 3 characters");
        }

        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
