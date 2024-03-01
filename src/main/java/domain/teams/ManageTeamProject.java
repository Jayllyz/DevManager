package domain.teams;

import domain.projects.Project;
import domain.projects.Developer;

public interface ManageTeamProject {
    Team getTeamForProject(Project project);

    Team addDeveloperToProject(Developer developer, Project project);

    Team removeDeveloperFromProject(Developer developer, Project project);
}
