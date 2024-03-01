package domain.teams;

import shared.developers.Email;
import shared.exceptions.InvalidAttributeException;

import java.util.List;

public class TeamManager implements ManageTeam, ManageTeamProject {
    TeamRepository teamRepository;

    public TeamManager(TeamRepository teamRepository) {
        if(teamRepository == null) {
            throw new IllegalArgumentException("repository can't be null");
        }
        this.teamRepository = teamRepository;
    }

    @Override
    public Team createTeam(Project project, List<Developer> developers) throws InvalidAttributeException {

        //TODO: FIND A BETTER EXCEPTION FOR THE TEAM CREATION
        Team team = new Team(project, developers);

        return this.teamRepository.createTeam(team);
    }

    @Override
    public Team getTeamForProject(domain.projects.Project project) {
        return this.teamRepository.getTeamForProject(project);
    }

    @Override
    public Team addDeveloperToProject(List<domain.projects.Developer> developers, domain.projects.Project project) {
        List<Email> developersEmail = developers.stream().map(developer -> developer.getEmail()).toList();
        return this.teamRepository.addDeveloperToProject(developersEmail, project);
    }

    @Override
    public Team removeDeveloperFromProject(List<domain.projects.Developer> developers, domain.projects.Project project) {
        return this.teamRepository.removeDeveloperFromProject(developers, project);
    }
}
