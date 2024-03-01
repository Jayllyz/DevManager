package domain.projects;

import shared.exceptions.InvalidAttributeException;

import java.util.HashSet;
import java.util.List;

public class Team {
    private Project project;
    private List<Developer> developers;

    public Team(Project project, List<Developer> developers) throws InvalidAttributeException {

        if(project == null) {
            throw new InvalidAttributeException("You need a project to create a team");
        }

        if(developers == null) {
            throw new InvalidAttributeException("Team developers are not defined");
        }

        if(teamHasDuplicatesDevelopers(developers)) {
            throw new InvalidAttributeException("There cannot be duplicates developers in the team");
        }

        this.developers = developers;
        this.project = project;

    }

    private boolean teamHasDuplicatesDevelopers(List<Developer> developers) {
        HashSet<String> seenEmail = new HashSet<>();

        for(Developer developer : developers) {
            String developerEmail = developer.getEmailAddress();
            if(seenEmail.contains(developerEmail)) {
                return true;
            }
            seenEmail.add(developerEmail);
        }

        return false;

    }
    public List<Developer> getDevelopers() {
        return developers;
    }
}

