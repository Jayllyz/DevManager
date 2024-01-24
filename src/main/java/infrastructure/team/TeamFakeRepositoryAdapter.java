package infrastructure.team;

import domain.teams.Team;
import domain.teams.TeamRepository;

import java.util.ArrayList;
import java.util.List;

public class TeamFakeRepositoryAdapter implements TeamRepository {
    public TeamFakeRepositoryAdapter() {
    }

    List<Team> teams = List.of(
            new Team("Team 1", new ArrayList<>()),
            new Team("Team 2", new ArrayList<>()),
            new Team("Team 3", new ArrayList<>())
    );

    @Override
    public Team createTeam(Team team) {
        teams.add(team);
        return team;
    }
}
