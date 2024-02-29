package infrastructure.developer.DTO;

import shared.Experience;
import shared.Skill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeveloperDTO {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private HashMap<Skill, Experience> skillByExperience;
    private List<ProjectDTO> projects;

    @JsonCreator
    public DeveloperDTO(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("emailAddress") String emailAddress,
            @JsonProperty("skillByExperience") Map<String, Integer> skillByExperience,
            @JsonProperty("projects") List<ProjectDTO> projects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.projects = projects;

        this.skillByExperience = new HashMap<>();
        for (Map.Entry<String, Integer> entry : skillByExperience.entrySet()) {

            String skillName = entry.getKey();
            skillName = skillName.toUpperCase();
            Skill skill = Skill.valueOf(skillName);

            int yearsOfExperience = entry.getValue();
            Experience experience = Experience.fromYearsOfExperience(yearsOfExperience);
            this.skillByExperience.put(skill, experience);
        }
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
