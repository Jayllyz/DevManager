package infrastructure.project.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import shared.Experience;
import shared.Skill;

import java.util.HashMap;

public class DeveloperDTO {
    private String emailAddress;
    private HashMap<String, Integer> skillByExperience;

    @JsonCreator
    public DeveloperDTO (
            @JsonProperty("email") String emailAddress,
            @JsonProperty("skill") HashMap<String, Integer> skillByExperience){
        this.emailAddress = emailAddress;
        this.skillByExperience = skillByExperience;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public HashMap<String, Integer> getSkillByExperience() {
        return skillByExperience;
    }
}
