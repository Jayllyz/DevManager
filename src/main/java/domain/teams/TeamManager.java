package domain.teams;

import domain.developers.Developer;
import shared.exceptions.InvalidAttributeException;

import java.util.List;

public class TeamManager implements ManageTeam {
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
}
