package infrastructure.project;

import domain.developers.ManageDeveloperProject;
import domain.developers.Projects;
import domain.projects.Developer;
import domain.projects.DeveloperManagement;
import domain.projects.Project;
import shared.developers.Email;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.EntityNotFoundException;
import shared.projects.Name;

import java.util.ArrayList;
import java.util.List;

public class DeveloperGateway implements DeveloperManagement {

    ManageDeveloperProject developerHexagon;

    public DeveloperGateway(ManageDeveloperProject developerHexagon) {
        this.developerHexagon = developerHexagon;
    }

    @Override
    public List<Developer> getAvailableDevelopersForProject(Name name) throws EntityNotFoundException {
        List<domain.developers.Developer> developersFromHexagon= developerHexagon.getAvailableDevelopersForProject(name);
        return transformDevelopersToProjectDomain(developersFromHexagon);
    }

    private List<Developer> transformDevelopersToProjectDomain(List<domain.developers.Developer> developers) {
        List<Developer> mappedDevelopers = new ArrayList<>();

        for(domain.developers.Developer developer : developers) {
            Developer transformedDeveloper = transformDeveloperForProject(developer);
            mappedDevelopers.add(transformedDeveloper);
        }

        return mappedDevelopers;
    }

    private Developer transformDeveloperForProject(domain.developers.Developer developer) {
        Email developerEmail = developer.getEmail();
        SkillsByYearsOfExperience skills = developer.getSkillsByYearsOfExperience();

        return new Developer(developerEmail,skills);
    }



}
