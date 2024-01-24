package domain.teams;

import domain.developers.Developer;

import java.util.List;

public class TeamManager implements ManageTeam {
    private TeamRepository teamRepository;

    @Override
    public Team createTeam(String name, List<Developer> developers) {
        Team team = new Team(name, developers);
        teamRepository.createTeam(team);
        return team;
    }
}
