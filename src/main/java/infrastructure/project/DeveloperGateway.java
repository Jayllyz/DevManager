package infrastructure.project;

import domain.developers.ManageDeveloperProject;
import domain.projects.Developer;
import domain.projects.DeveloperManagement;
import shared.projects.Name;

import java.util.List;

public class DeveloperGateway implements DeveloperManagement {

    ManageDeveloperProject developerHexagon;

    public DeveloperGateway(ManageDeveloperProject developerHexagon) {
        this.developerHexagon = developerHexagon;
    }

    @Override
    public List<Developer> getAvailableDevelopersForProject(Name name) {
        return null;
    }
}
