package domain.developers;

import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.exceptions.EntityNotFoundException;

import java.util.List;

public interface DeveloperRepository {
    Developer createDeveloper(Developer developer);
    Developer getDeveloperByMail(Email email) throws EntityNotFoundException;
    void removeDeveloper(Email email);
    List<Developer> getAllDevelopers();
    List<Developer> getAllDevelopersBySkill(Skill skill);
    List<Developer> getAllDevelopersBySkillAndExperience(Skill skill, Experiencgie experience);
}
