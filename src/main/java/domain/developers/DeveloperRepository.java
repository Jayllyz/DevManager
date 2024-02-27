package domain.developers;

import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.exceptions.EntityAlreadyExistsException;
import shared.exceptions.EntityNotFoundException;
import shared.projects.Deadline;
import shared.projects.Name;
import shared.projects.SkillStack;
import shared.projects.StartDate;

import java.util.List;

public interface DeveloperRepository {
    Developer createDeveloper(Developer developer);
    Developer getDeveloperByMail(Email email) throws EntityNotFoundException;
    void removeDeveloper(Email email);
    List<Developer> getAllDevelopers();
    List<Developer> getAllDevelopersBySkill(Skill skill);
    List<Developer> getAllDevelopersBySkillAndExperience(Skill skill, Experience experience);
    List<Developer> getAvailableDevelopersForProject(Name name) throws EntityAlreadyExistsException;

    List<Developer> getAvailableDevelopersForProject(StartDate start, Deadline deadline, SkillStack skillsNeeded) throws EntityAlreadyExistsException;
}
