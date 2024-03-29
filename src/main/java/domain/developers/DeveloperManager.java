package domain.developers;

import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.exceptions.EntityAlreadyExistsException;
import shared.exceptions.EntityNotFoundException;
import shared.exceptions.InvalidAttributeException;
import shared.projects.Name;


import java.util.List;

/**
 * The type Developer manager.
 */
// HEXAGON
public class DeveloperManager implements ManageDeveloper, ManageDeveloperProject{
    DeveloperRepository repository;

    public DeveloperManager(DeveloperRepository repository) {

        if(repository == null) {
            throw new IllegalArgumentException("repository can't be null");
        }

        this.repository = repository;
    }


    @Override
    public Developer createDeveloper(Developer developer) throws EntityAlreadyExistsException, InvalidAttributeException {
        if(developerExist(new Email(developer.getEmailAddress()))) {
            throw new EntityAlreadyExistsException("The email provided is already used by a developer");
        }

        return this.repository.createDeveloper(developer);
    }

    private boolean developerExist(Email email) {
        try {
            Developer developer = this.repository.getDeveloperByMail(email);
        } catch (EntityNotFoundException e) {
            return false;
        }

        return true;
    }

    @Override
    public Developer getDeveloperByMail(Email email) throws EntityNotFoundException {
        return repository.getDeveloperByMail(email);
    }

    @Override
    public Email removeDeveloper(Email email) {
        if(!developerExist(email)) {
            throw new EntityNotFoundException("The developer with the email provided does not exist");
        }
        this.repository.removeDeveloper(email);
        return email;
    }


    @Override
    public List<Developer> getAllDevelopers() {
        return repository.getAllDevelopers();
    }

    @Override
    public List<Developer> getAllDevelopersBySkill(Skill skill) {
        return this.repository.getAllDevelopersBySkill(skill);
    }

    @Override
    public List<Developer> getAllDevelopersBySkillAndExperience(Skill skill, Experience experience) {
        return this.repository.getAllDevelopersBySkillAndExperience(skill, experience);
    }

    @Override
    public List<Developer> getAvailableDevelopersForProject(Name name) throws EntityNotFoundException {
        return repository.getAvailableDevelopersForProject(name);
    }
}
