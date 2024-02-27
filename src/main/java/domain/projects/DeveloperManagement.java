package domain.projects;

import shared.exceptions.EntityNotFoundException;
import shared.exceptions.InvalidAttributeException;
import shared.projects.Name;

import java.util.List;

public interface DeveloperManagement {
    List<Developer> getAvailableDevelopersForProject(Name name) throws EntityNotFoundException, InvalidAttributeException;
}
