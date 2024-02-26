package domain.developers;

import shared.exceptions.EntityNotFoundException;
import shared.projects.Name;

import java.util.List;

public interface ManageDeveloperProject {
    List<Developer> getAvailableDevelopersForProject(Name name) throws EntityNotFoundException;
}
