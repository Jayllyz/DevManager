package infrastructure.gateway;

import domain.developers.Developer;
import domain.developers.ManageDeveloperProject;
import shared.exceptions.EntityNotFoundException;
import shared.projects.Name;

import java.util.List;

public class ProjectManageDeveloper implements ManageDeveloperProject {

    @Override
    public List<Developer> getAvailableDevelopersForProject(Name name) throws EntityNotFoundException {
        return null;
    }
}
