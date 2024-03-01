package infrastructure.project.DTO;

import domain.projects.Developer;
import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.developers.SkillsByYearsOfExperience;

import java.util.HashMap;

public class DeveloperMapper {
    public static DeveloperDTO mapDeveloperToDTO(Developer developer) {
        String emailAddress = developer.getEmailAddress();
        SkillsByYearsOfExperience skillsObject = developer.getSkillsByYearsOfExperience();

        HashMap<Skill, Experience> skillByExperience = new HashMap<>();
        for (Skill skill : skillsObject.getSkills()) {
            skillByExperience.put(skill, skillsObject.getSkillExperience(skill));
        }

        HashMap<String, Integer> skillByExperienceSimple = new HashMap<>();
        for (Skill skill : skillByExperience.keySet()) {
            skillByExperienceSimple.put(skill.toString(), skillByExperience.get(skill).getYearsOfExperience());
        }

        return new DeveloperDTO(emailAddress, skillByExperienceSimple);
    }

    public static Developer mapDTOToDeveloper(DeveloperDTO developerDTO) {
        String emailAddress = developerDTO.getEmailAddress();
        HashMap<String, Integer> skillByExperience = developerDTO.getSkillByExperience();
        HashMap<Skill, Experience> skills = new HashMap<>();

        if(skillByExperience == null) {
            return new Developer(new Email(emailAddress), new SkillsByYearsOfExperience(skills));
        }

        for (String skill : skillByExperience.keySet()) {
            skills.put(Skill.valueOf(skill), Experience.fromYearsOfExperience(skillByExperience.get(skill)));
        }

        SkillsByYearsOfExperience skillsByYearsOfExperience = new SkillsByYearsOfExperience(skills);

        return new Developer(new Email(emailAddress), skillsByYearsOfExperience);
    }
}
