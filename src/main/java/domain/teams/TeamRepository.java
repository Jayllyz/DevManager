package domain.teams;

import domain.projects.Developer;
import domain.projects.Project;
import shared.developers.Email;

import java.util.List;

public interface TeamRepository {
    Team createTeam(Team team);
    Team getTeamForProject(domain.projects.Project project);
    Team addDeveloperToProject(List<Email> developersEmail, domain.projects.Project project);
    Team removeDeveloperFromProject(List<Developer> developers, Project project);
}
