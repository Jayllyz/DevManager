package infrastructure.project.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import shared.Experience;
import shared.Skill;

import java.util.HashMap;

public class DeveloperDTO {
    private String emailAddress;
    private HashMap<Skill, Experience> skillByExperience;

    public DeveloperDTO (String emailAddress, HashMap<Skill, Experience> skillByExperience){
        this.emailAddress = emailAddress;
        this.skillByExperience = skillByExperience;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public HashMap<Skill, Experience> getSkillByExperience() {
        return skillByExperience;
    }
}
