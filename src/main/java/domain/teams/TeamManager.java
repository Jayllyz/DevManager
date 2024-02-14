package domain.teams;

import domain.developers.Developer;
import shared.exceptions.InvalidAttributeException;
import shared.teams.Developers;
import shared.Skill;
import shared.teams.Developers;
import shared.teams.Name;

import java.util.HashMap;
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
    public Team createTeam(String name, List<Developer> developers) {
        Team team;
        try{
            team = new Team(new Name(name), new Developers(developers));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return this.teamRepository.createTeam(team);
    }
}
