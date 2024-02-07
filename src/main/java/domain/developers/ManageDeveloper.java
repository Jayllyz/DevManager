package domain.developers;

import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.exceptions.EntityAlreadyExistsException;
import shared.exceptions.EntityNotFoundException;

import java.util.List;

public interface ManageDeveloper {
    Developer createDeveloper(Developer developer) throws EntityAlreadyExistsException;
    Developer getDeveloperByMail(Email email) throws EntityNotFoundException;
    Email removeDeveloper(Email email);
    Developer updateDeveloper(Developer developer);
    List<Developer> getAllDevelopers();
    List<Developer> getAllDevelopersBySkill(Skill skill);
    List<Developer> getAllDevelopersBySkillAndExperience(Skill skill, Experience experience);


//    Developer getAllDevelopersByExperience(Skill skill, Experience experience)
}
