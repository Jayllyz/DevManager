package infrastructure.team;

import domain.developers.Developer;
import domain.teams.Team;
import domain.teams.TeamRepository;
import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.InvalidAttributeException;
import shared.teams.Developers;
import shared.teams.Name;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamFakeRepositoryAdapter implements TeamRepository {
    HashMap<Skill, Experience> skillSet1;
    HashMap<Skill, Experience> skillSet2;
    HashMap<Skill, Experience> skillSet3;
    {
        try{
            skillSet1 = new HashMap<>(
                Map.of(
                        Skill.PHP, Experience.fromYearsOfExperience(2),
                        Skill.COBOL, Experience.fromYearsOfExperience(1),
                        Skill.COFFEE, Experience.fromYearsOfExperience(6),
                        Skill.HTML, Experience.fromYearsOfExperience(14)
                )
            );
            skillSet2 = new HashMap<>(
                Map.of(
                        Skill.PHP, Experience.fromYearsOfExperience(1),
                        Skill.COBOL, Experience.fromYearsOfExperience(4),
                        Skill.CSS, Experience.fromYearsOfExperience(1),
                        Skill.SCRATCH, Experience.fromYearsOfExperience(23)
                )
            );
            skillSet3 = new HashMap<>(
                Map.of(
                        Skill.PHP, Experience.fromYearsOfExperience(2),
                        Skill.COBOL, Experience.fromYearsOfExperience(4),
                        Skill.COFFEE, Experience.fromYearsOfExperience(1),
                        Skill.HTML, Experience.fromYearsOfExperience(2)
                )
            );
        } catch (InvalidAttributeException e) {
            throw new RuntimeException(e);
        }
    }

    public TeamFakeRepositoryAdapter() {
    }
    List<Developer> developers;

    {
        try {
            developers = List.of(
                    new Developer(new shared.developers.Name("john"),new shared.developers.Name("Doe"),new Email("johndoe@gmail.com"),new SkillsByYearsOfExperience(skillSet1)),
                    new Developer(new shared.developers.Name("Marc"),new shared.developers.Name("Robel"),new Email("marc@gmail.com"),new SkillsByYearsOfExperience(skillSet2)),
                    new Developer(new shared.developers.Name("Jeanne"),new shared.developers.Name("Darc"),new Email("jeanne@gmail.com"),new SkillsByYearsOfExperience(skillSet3))
            );
        } catch (InvalidAttributeException e) {
            throw new RuntimeException(e);
        }
    }

    List<Team> teams;
    {
        try{
            teams = List.of(
                    new Team(new Name("Team 1"), new Developers(developers))
            );
        } catch (InvalidAttributeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Team createTeam(Team team) {
        teams.add(team);
        return team;
    }
}
