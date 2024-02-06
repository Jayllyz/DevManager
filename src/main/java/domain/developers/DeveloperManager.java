package domain.developers;

import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.exceptions.NoEntityFoundException;

import java.util.List;

/**
 * The type Developer manager.
 */
// HEXAGON
public class DeveloperManager implements ManageDeveloper{
    DeveloperRepository repository;

    public DeveloperManager(DeveloperRepository repository) {

        if(repository == null) {
            throw new IllegalArgumentException("repository can't be null");
        }

        this.repository = repository;
    }


    @Override
    public Developer createDeveloper(Developer developer) {
        return this.repository.createDeveloper(developer);
    }

    @Override
    public Developer getDeveloperByMail(Email email) throws NoEntityFoundException {
        return repository.getDeveloperByMail(email.toString());
    }

    @Override
    public boolean removeDeveloper(Email email) {
        return false;
    }

    @Override
    public Developer updateDeveloper(Developer developer) {
        return null;
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return repository.getAllDevelopers();
    }

    @Override
    public List<Developer> getAllDevelopersBySkill(Skill skill) {
        return null;
    }

    @Override
    public List<Developer> getAllDevelopersBySkillAndExperience(Skill skill, Experience experience) {
        return null;
    }


}
