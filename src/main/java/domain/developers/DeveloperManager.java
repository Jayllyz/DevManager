package domain.developers;

import shared.Experience;
import shared.Skill;
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
        this.repository.createDeveloper(developer);
        return developer;
    }

    @Override
    public Developer getDeveloperByMail(String email) throws NoEntityFoundException {
        return repository.getDeveloperByMail(email);
    }

    @Override
    public boolean removeDeveloper(String email) {
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
