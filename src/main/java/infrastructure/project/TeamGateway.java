package infrastructure.project;

import domain.projects.Developer;
import domain.projects.Project;
import domain.projects.Team;
import domain.projects.TeamManagement;
import domain.teams.ManageTeamProject;

import java.util.ArrayList;
import java.util.List;

public class TeamGateway implements TeamManagement {

    ManageTeamProject teamHexagon;

    public TeamGateway(ManageTeamProject teamHexagon) {
        this.teamHexagon = teamHexagon;
    }

    @Override
    public Team getTeamForProject(Project project) {
        domain.teams.Team team = teamHexagon.getTeamForProject(project);
        return transformTeamToProjectDomain(team, project);
    }

    @Override
    public Team addDeveloperToProject(List<Developer> developers, Project project) {
        domain.teams.Team team = teamHexagon.addDeveloperToProject(developers, project);
        return transformTeamToProjectDomain(team, project);
    }

    @Override
    public Team removeDeveloperFromProject(List<Developer> developers, Project project) {
        domain.teams.Team team = teamHexagon.removeDeveloperFromProject(developers, project);
        return transformTeamToProjectDomain(team, project);
    }

    private Team transformTeamToProjectDomain(domain.teams.Team team, Project project) {
        List<Developer> transformedDevelopers = new ArrayList<>();

        for (domain.teams.Developer developer : team.getDevelopers()) {
            domain.projects.Developer transformedDeveloper = new domain.projects.Developer(developer.getEmail(), developer.getSkillsByYearsOfExperience());
            transformedDevelopers.add(transformedDeveloper);
        }

        return new domain.projects.Team(project, transformedDevelopers);
    }
}
