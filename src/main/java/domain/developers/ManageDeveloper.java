package domain.developers;

import shared.Experience;
import shared.Skill;
import shared.exceptions.NoEntityFoundException;

import java.util.List;

public interface ManageDeveloper {
    Developer createDeveloper(Developer developer);
    Developer getDeveloperByMail(String email) throws NoEntityFoundException;
    boolean removeDeveloper(String email);
    Developer updateDeveloper(Developer developer);
    List<Developer> getAllDevelopers();
    List<Developer> getAllDevelopersBySkill(Skill skill);
    List<Developer> getAllDevelopersBySkillAndExperience(Skill skill, Experience experience);


//    Developer getAllDevelopersByExperience(Skill skill, Experience experience)
}
