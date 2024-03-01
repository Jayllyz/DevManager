package infrastructure.developer.driving;

import domain.developers.ManageDeveloperProject;
import domain.projects.Developer;
import domain.projects.DeveloperManagement;
import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.EntityNotFoundException;
import shared.exceptions.InvalidAttributeException;
import shared.projects.Name;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeveloperGateway implements DeveloperManagement {

    ManageDeveloperProject developerHexagon;

    public DeveloperGateway(ManageDeveloperProject developerHexagon) {
        this.developerHexagon = developerHexagon;
    }

    @Override
    public List<Developer> getAvailableDevelopersForProject(Name name) throws EntityNotFoundException, InvalidAttributeException {
        List<domain.developers.Developer> developersFromHexagon= developerHexagon.getAvailableDevelopersForProject(name);
        return transformDevelopersToProjectDomain(developersFromHexagon);
    }

    private List<Developer> transformDevelopersToProjectDomain(List<domain.developers.Developer> developers) throws InvalidAttributeException {
        List<Developer> mappedDevelopers = new ArrayList<>();

        for(domain.developers.Developer developer : developers) {
            Developer transformedDeveloper = transformDeveloperForProject(developer);
            mappedDevelopers.add(transformedDeveloper);
        }

        return mappedDevelopers;
    }

    private Developer transformDeveloperForProject(domain.developers.Developer developer) throws InvalidAttributeException {
        String developerEmail = developer.getEmailAddress();
        HashMap<Skill, Experience> skills = developer.getSkillsByYearsOfExperience();

        try {
            return new Developer(new Email(developerEmail), new SkillsByYearsOfExperience(skills));
        } catch (InvalidAttributeException e) {
            throw new InvalidAttributeException("Developer has invalid attributes");
        }
    }
}
