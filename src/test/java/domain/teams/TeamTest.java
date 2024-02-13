package domain.teams;

import domain.developers.Developer;
import infrastructure.team.TeamFakeRepositoryAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.InvalidAttributeException;
import shared.teams.Developers;
import shared.teams.Name;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    private final TeamManager teanManager;

    public TeamTest() {
        TeamRepository repository = new TeamFakeRepositoryAdapter();
        this.teanManager = new TeamManager(repository);
    }

    @Test
    @DisplayName("Should create a team")
    void shouldCreateATeam() {
        HashMap<Skill, Experience> skillSet1 = new HashMap<>();
        HashMap<Skill, Experience> skillSet2 = new HashMap<>();
        HashMap<Skill, Experience> skillSet3 = new HashMap<>();
        try {
            skillSet1.put(Skill.HTML, Experience.fromYearsOfExperience(4));
            skillSet2.put(Skill.SCRATCH, Experience.fromYearsOfExperience(4));
            skillSet3.put(Skill.COBOL, Experience.fromYearsOfExperience(4));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        Developer developer1;
        Developer developer2;
        Developer developer3;
        try {
            developer1 = new Developer(new shared.developers.Name("john"), new shared.developers.Name("Doe"), new Email("johndoe@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer2 = new Developer(new shared.developers.Name("Marc"), new shared.developers.Name("Robel"), new Email("marc@gmail.com"), new SkillsByYearsOfExperience(skillSet2));
            developer3 = new Developer(new shared.developers.Name("Jeanne"), new shared.developers.Name("Darc"), new Email("jeanne@gmail.com"), new SkillsByYearsOfExperience(skillSet3));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        List<Developer> listDeveloper = List.of(
                developer1,
                developer2,
                developer3
        );

        Team team;
        try {
            team = new Team(new Name("Test"), new Developers(listDeveloper));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        assertInstanceOf(Team.class, team);
        assertNotNull(team);
    }

    @Test
    @DisplayName("Should create a team with the function")
    void shouldCreateATeamWithTheFunction() {
        HashMap<Skill, Experience> skillSet1 = new HashMap<>();
        HashMap<Skill, Experience> skillSet2 = new HashMap<>();
        HashMap<Skill, Experience> skillSet3 = new HashMap<>();
        try {
            skillSet1.put(Skill.HTML, Experience.fromYearsOfExperience(4));
            skillSet2.put(Skill.SCRATCH, Experience.fromYearsOfExperience(4));
            skillSet3.put(Skill.COBOL, Experience.fromYearsOfExperience(4));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        Developer developer1;
        Developer developer2;
        Developer developer3;
        try {
            developer1 = new Developer(new shared.developers.Name("Tyrion"), new shared.developers.Name("Fordring"), new Email("tf@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer2 = new Developer(new shared.developers.Name("Onyxia"), new shared.developers.Name("AileNoir"), new Email("oa@gmail.com"), new SkillsByYearsOfExperience(skillSet2));
            developer3 = new Developer(new shared.developers.Name("Nefarian"), new shared.developers.Name("AileNoir"), new Email("na@gmail.com"), new SkillsByYearsOfExperience(skillSet3));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        List<Developer> listDeveloper = List.of(
                developer1,
                developer2,
                developer3
        );

        Team team = teanManager.createTeam("Test", listDeveloper);
        assertInstanceOf(Team.class, team);
    }
}