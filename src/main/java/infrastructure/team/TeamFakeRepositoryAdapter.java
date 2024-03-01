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
import shared.projects.Name;
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
    List<Project> project1;

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

        this.project1 = new ArrayList<>(List.of(
                new Project(new Name("Calculator"), Priority.NORMAL, new StartDate(LocalDate.now().plusDays(1)), new Deadline(LocalDate.now().plusDays(20)), skillsNeeded, Status.CANCELLED),
                new Project(new Name("Spotify"), Priority.CRITICAL, new StartDate(LocalDate.now().plusDays(1)), new Deadline(LocalDate.now().plusDays(20)), skillsNeeded, Status.IN_PROGRESS),
                new Project(new Name("jsp"), Priority.NORMAL, new StartDate(LocalDate.now().plusDays(1)), new Deadline(LocalDate.now().plusDays(20)), skillsNeeded, Status.DONE)
        ));

        teams = new ArrayList<>(List.of(
                new Team(project1.get(0)),
                new Team(project1.get(1)),
                new Team(project1.get(2))
        ));
    }


    @Override
    public Team createTeam(Team team) {
        teams.add(team);
        return team;
    }

    @Override
    public Team getTeamForProject(domain.projects.Project project) {
        String projectName = project.getName();

        for (Team team : teams) {
            if (team.getProject().getName().equals(projectName)) {
                return team;
            }
        }

        SkillStack skillStack = new SkillStack();
        for (Skill skill : project.getStack().keySet()) {
            skillStack.put(skill, project.getStack().get(skill));
        }

        Project newProject = new Project(
                new Name(project.getName()),
                project.getPriority(),
                new StartDate(project.getStart()),
                new Deadline(project.getDeadline()),
                skillStack,
                project.getStatus()
        );

        return new Team(newProject, new ArrayList<>());
    }

    @Override
    public Team addDeveloperToProject(List<domain.projects.Developer> developers, domain.projects.Project project) {
        Team team = getTeamForProject(project);
        for (domain.projects.Developer developer : developers) {
            Developer newDeveloper = new Developer(developer.getEmail(), developer.getSkillsByYearsOfExperience());
            team.addDeveloper(newDeveloper);
        }
        return team;
    }

    @Override
    public Team removeDeveloperFromProject(List<domain.projects.Developer> developers, domain.projects.Project project) {
        Team team = getTeamForProject(project);
        for (domain.projects.Developer developer : developers) {
            Developer newDeveloper = new Developer(developer.getEmail(), developer.getSkillsByYearsOfExperience());
            team.removeDeveloper(newDeveloper);
        }
        return team;
    }
}
