package infrastructure.developer.DTO;

import shared.Experience;
import shared.Skill;

import java.util.HashMap;
import java.util.List;

public class DeveloperDTO {
    private final String firstName;

    private final String lastName;
    private final String emailAddress;

    private final HashMap<Skill, Experience> skillByExperience;
    private final List<ProjectDTO> projects;

    public DeveloperDTO(String firstName, String lastName, String emailAddress, HashMap<Skill, Experience> skillByExperience, List<ProjectDTO> projects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.skillByExperience = skillByExperience;
        this.projects = projects;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public HashMap<Skill, Experience> getSkillByExperience() {
        return skillByExperience;
    }

    public List<ProjectDTO> getProjects() {
        return projects;
    }
}
