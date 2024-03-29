package domain.teams;

import shared.Experience;
import shared.Priority;
import shared.developers.Email;
import shared.exceptions.InvalidAttributeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Team {
    private final Project project;
    private List<Developer> developers;

    public Team(Project project){
        this.project = project;
        this.developers = new ArrayList<>();
    }

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

        this.developers = new ArrayList<>();
        this.developers.addAll(developers);
        this.project = project;

        validateForProject();

    }

    public void addDeveloper(Developer developer) throws InvalidAttributeException {

        if(developerInTeam(developer)) {
            throw new InvalidAttributeException("Developer already exist in team");
        }

        developers.add(developer);
    }

    public void removeDeveloper(Developer developer) throws InvalidAttributeException {
        if(!developerInTeam(developer)) {
            throw new InvalidAttributeException("Developer does not exist in team");
        }

        developers.remove(developer);
    }

    private boolean developerInTeam(Developer developer) {

        Email developerEmail = developer.getEmail();

        for(Developer teamDeveloper : developers) {
            Email currentEmail = teamDeveloper.getEmail();
            if(developerEmail.equals(currentEmail)) return true;
        }
        return false;
    }

    public void validateForProject() throws InvalidAttributeException {
        if(developers.size() < 3){
            throw new InvalidAttributeException("Team must have at least 3 developers");
        }

        if(developers.size() > 8){
            throw new InvalidAttributeException("Team can't have more than 8 developers");
        }

        HashMap<Experience,Integer> numberOfDeveloperByExperience = getTeamExperience(developers);

        int juniorCount = numberOfDeveloperByExperience.get(Experience.JUNIOR);
        int expertCount = numberOfDeveloperByExperience.get(Experience.EXPERT);


        if(expertCount == 0){

            validateTeamWithoutExpert(juniorCount,project);

        } else {

            validateTeamWithExpert(juniorCount,project,developers);
        }
    }

    private void validateTeamWithoutExpert(int juniorCount, Project project) throws InvalidAttributeException {
        if(juniorCount > 0) {
            throw new InvalidAttributeException("Team can't have a junior developer without an expert developer");

        }

        int test = project.getDurationInMonth();
        if(project.getDurationInMonth() > 6) {
            throw new InvalidAttributeException("Team need an expert if the project is longer than 6 months");
        }
    }

    private void validateTeamWithExpert(int juniorCount, Project project,List<Developer> developers) throws InvalidAttributeException {
        if(developers.size() < 5 && project.getPriority() != Priority.CRITICAL) {
            throw new InvalidAttributeException("An expert cannot be in a team with less than 5 developers if the project is not critical");
        }

        if(juniorCount > 3){
            throw new InvalidAttributeException("Team can't have more than 3 junior developers");
        }
    }

    private HashMap<Experience,Integer> getTeamExperience(List<Developer> developers) {
        HashMap<Experience,Integer> experiences = new HashMap<>();

        experiences.put(Experience.JUNIOR,0);
        experiences.put(Experience.SKILLED,0);
        experiences.put(Experience.EXPERT,0);

        for(Developer developer : developers) {
            Experience developerExperience = developer.getGlobalExperience();
            int currentCount = experiences.get(developerExperience);
            experiences.put(developerExperience,currentCount + 1);
        }

        return experiences;
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

    public Project getProject() {
        return project;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }
}

