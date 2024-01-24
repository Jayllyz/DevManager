package domain.teams;

import infrastructure.team.TeamFakeRepositoryAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TeamTeast {
    TeamRepository teamRepository = new TeamFakeRepositoryAdapter();

    @Test
    @DisplayName("Should create a team")
    void shouldCreateATeam() {
        Team team = new Team("Team 1", null);
        assertInstanceOf(Team.class, team);
        assertNotNull(team);
    }
}
