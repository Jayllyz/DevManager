package domain.teams;

import java.util.List;

public interface TeamRepository {
    Team createTeam(Team team);
    Team getTeamForProject(domain.projects.Project project);
    Team addDevelopersToProject(domain.projects.Project project, List<domain.projects.Developer> developers);
    Team removeDevelopersFromProject(domain.projects.Project project, List<domain.projects.Developer> developers);
}
