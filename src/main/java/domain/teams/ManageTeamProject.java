package domain.teams;

import domain.projects.Project;
import domain.projects.Developer;

import java.util.List;

public interface ManageTeamProject {
    Team getTeamForProject(Project project);

    Team addDeveloperToProject(List<Developer> developers, Project project);

    Team removeDeveloperFromProject(List<Developer> developers, Project project);
}
