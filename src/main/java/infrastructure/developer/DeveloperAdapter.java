package infrastructure.developer;

import domain.developers.Developer;
import domain.developers.DeveloperManager;
import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.developers.Name;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.EntityAlreadyExistsException;
import shared.exceptions.EntityNotFoundException;
import shared.exceptions.InvalidAttributeException;

import java.util.HashMap;
import java.util.List;

public class DeveloperAdapter {

    private DeveloperManager developerManager;

    public DeveloperAdapter(DeveloperManager developerManager) {
        this.developerManager = developerManager;
    }

    public Developer createDeveloper(String emailAddress, String firstName, String lastName, HashMap<Skill, Experience> skills) throws InvalidAttributeException {
        Developer developer = new Developer(
                new Name(firstName),
                new Name(lastName),
                new Email(emailAddress),
                new SkillsByYearsOfExperience(skills),
                null
        );
        try {
            return developerManager.createDeveloper(developer);
        } catch (EntityAlreadyExistsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Developer getDeveloperByEmail(String emailAddress) throws EntityNotFoundException, InvalidAttributeException {
        Email email = new Email(emailAddress);
        return developerManager.getDeveloperByMail(email);
    }

    public void removeDeveloperByEmail(String emailAddress) throws InvalidAttributeException {
        Email email = new Email(emailAddress);
        developerManager.removeDeveloper(email);
    }

    public List<Developer> getAllDevelopers() {
        return developerManager.getAllDevelopers();
    }

    public List<Developer> getAllDevelopersBySkill(Skill skill) {
        return developerManager.getAllDevelopersBySkill(skill);
    }

    public List<Developer> getAllDevelopersBySkillAndExperience(Skill skill, Experience experience) {
        return developerManager.getAllDevelopersBySkillAndExperience(skill, experience);
    }
}
