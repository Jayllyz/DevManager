package domain.projects;

import java.util.List;

public interface TeamManagement {
    Team getTeamForProject(Project project);
    Team addDeveloperToProject(List<Developer> developer, Project project);
    Team removeDeveloperFromProject(List<Developer> developer, Project project);
}
