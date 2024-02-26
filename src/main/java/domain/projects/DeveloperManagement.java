package domain.projects;

import shared.projects.Name;

import java.util.List;

public interface DeveloperManagement {
    List<Developer> getAvailableDevelopersForProject(Name name);
}
