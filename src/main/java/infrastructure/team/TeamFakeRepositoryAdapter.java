package infrastructure.team;

import domain.developers.Developer;
import domain.teams.Project;
import domain.teams.Team;
import domain.teams.TeamRepository;
import shared.Experience;
import shared.Priority;
import shared.Skill;
import shared.developers.Email;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.InvalidAttributeException;
import shared.projects.Deadline;
import shared.projects.SkillStack;
import shared.projects.StartDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamFakeRepositoryAdapter implements TeamRepository {

    List<Developer> developers;
    List<Team> teams;
    Project project1;

    public TeamFakeRepositoryAdapter() {

        HashMap<Skill, Experience> skillSet1;
        HashMap<Skill, Experience> skillSet2;
        HashMap<Skill, Experience> skillSet3;


        try {

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

            developers = List.of(
                    new Developer(new shared.developers.Name("john"),new shared.developers.Name("Doe"),new Email("johndoe@gmail.com"),new SkillsByYearsOfExperience(skillSet1)),
                    new Developer(new shared.developers.Name("Marc"),new shared.developers.Name("Robel"),new Email("marc@gmail.com"),new SkillsByYearsOfExperience(skillSet2)),
                    new Developer(new shared.developers.Name("Jeanne"),new shared.developers.Name("Darc"),new Email("jeanne@gmail.com"),new SkillsByYearsOfExperience(skillSet3)),
                    new Developer(new shared.developers.Name("danzo"),new shared.developers.Name("Darc"),new Email("fda@gmail.com"),new SkillsByYearsOfExperience(skillSet3)),
                    new Developer(new shared.developers.Name("popo"),new shared.developers.Name("Darc"),new Email("fsfsf@gmail.com"),new SkillsByYearsOfExperience(skillSet3))
            );

            LocalDate tomorrow = LocalDate.now().plusDays(1);
            LocalDate in7Months = LocalDate.now().plusMonths(7);

            SkillStack skillsNeeded = new SkillStack();
            skillsNeeded.put(Skill.SCRATCH,1);
            skillsNeeded.put(Skill.HTML,3);

            this.project1 = new Project(Priority.NORMAL,new StartDate(tomorrow),new Deadline(in7Months),skillsNeeded);

            teams = new ArrayList<> (List.of(
                    new Team(project1, developers)
            ));

        } catch (Exception e) {
            throw  new RuntimeException();
        }
    }


    @Override
    public Team createTeam(Team team) {
        teams.add(team);
        return team;
    }
}
