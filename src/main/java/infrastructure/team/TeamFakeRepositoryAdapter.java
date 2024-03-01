package infrastructure.team;

import domain.teams.Developer;
import domain.teams.Project;
import domain.teams.Team;
import domain.teams.TeamRepository;
import shared.Experience;
import shared.Priority;
import shared.Skill;
import shared.Status;
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

    public TeamFakeRepositoryAdapter() throws InvalidAttributeException {

        HashMap<Skill, Experience> skillSet1;
        HashMap<Skill, Experience> skillSet2;
        HashMap<Skill, Experience> skillSet3;


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
                new Developer(new Email("johndoe@gmail.com"),new SkillsByYearsOfExperience(skillSet1)),
                new Developer(new Email("marc@gmail.com"),new SkillsByYearsOfExperience(skillSet2)),
                new Developer(new Email("jeanne@gmail.com"),new SkillsByYearsOfExperience(skillSet3)),
                new Developer(new Email("fda@gmail.com"),new SkillsByYearsOfExperience(skillSet3)),
                new Developer(new Email("fsfsf@gmail.com"),new SkillsByYearsOfExperience(skillSet3))
        );

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate in7Months = LocalDate.now().plusMonths(7);

        SkillStack skillsNeeded = new SkillStack();
        skillsNeeded.put(Skill.SCRATCH,1);
        skillsNeeded.put(Skill.HTML,3);

        this.project1 = new Project(new shared.projects.Name("Spotify"),Priority.NORMAL,new StartDate(tomorrow),new Deadline(in7Months),skillsNeeded, Status.DONE);

        teams = new ArrayList<> (List.of(
                new Team(project1, developers)
        ));

    }


    @Override
    public Team createTeam(Team team) {
        teams.add(team);
        return team;
    }

    @Override
    public Team getTeamForProject(domain.projects.Project project) {
        for(Team team : teams) {
            if(team.getProject().equals(project)) {
                return team;
            }
        }
        throw new IllegalArgumentException("No team found for this project");
    }

    @Override
    public Team addDevelopersToProject(domain.projects.Project project, List<domain.projects.Developer> developers) {
        Team team = getTeamForProject(project);
        for (domain.projects.Developer developer : developers) {
            Developer newDeveloper = new Developer(developer.getEmail(), developer.getSkillsByYearsOfExperience());
            team.addDeveloper(newDeveloper);
        }
        return team;
    }

    @Override
    public Team removeDevelopersFromProject(domain.projects.Project project, List<domain.projects.Developer> developers) {
        Team team = getTeamForProject(project);
        for (domain.projects.Developer developer : developers) {
            Developer newDeveloper = new Developer(developer.getEmail(), developer.getSkillsByYearsOfExperience());
            team.removeDeveloper(newDeveloper);
        }
        return team;
    }
}
