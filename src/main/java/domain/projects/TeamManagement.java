package domain.projects;

public interface TeamManagement {
    Team getTeamForProject(Project project);
    Team addDeveloperToProject(Developer developer, Project project);
    Team removeDeveloperFromProject(Developer developer, Project project);
}
