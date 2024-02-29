package infrastructure.project.DTO;

import domain.projects.Developer;
import shared.Experience;
import shared.Skill;
import shared.developers.SkillsByYearsOfExperience;

import java.util.HashMap;

public class DeveloperMapper {
    public static DeveloperDTO mapDeveloperToDTO(Developer developer) {
        String emailAddress = developer.getEmailAddress();
        SkillsByYearsOfExperience skillsByYearsOfExperience = developer.getSkillsByYearsOfExperience();
        HashMap<Skill, Experience> skills = new HashMap<>();
        for (Skill skill : skillsByYearsOfExperience.getSkills()) {
            skills.put(skill, skillsByYearsOfExperience.getSkillExperience(skill));
        }

        return new DeveloperDTO(emailAddress, skills);
    }
}
