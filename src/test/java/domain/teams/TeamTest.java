package domain.teams;

import infrastructure.team.TeamFakeRepositoryAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    private final TeamManager teamManager;

    Project projectNormal;
    Project projectCritical;

    public TeamTest() throws InvalidAttributeException {
        TeamRepository repository = new TeamFakeRepositoryAdapter();
        this.teamManager = new TeamManager(repository);

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate in7Months = LocalDate.now().plusMonths(7);

        SkillStack skillsNeeded = new SkillStack();
        skillsNeeded.put(Skill.SCRATCH,1);
        skillsNeeded.put(Skill.HTML,3);

        this.projectNormal = new Project(new shared.projects.Name("Spotify"),Priority.NORMAL,new StartDate(tomorrow),new Deadline(in7Months),skillsNeeded, Status.WAITING);
        this.projectCritical = new Project(new shared.projects.Name("Spotify2"),Priority.CRITICAL,new StartDate(tomorrow),new Deadline(in7Months),skillsNeeded, Status.WAITING);
    }

    @Test
    @DisplayName("should throw exception they are less than 3 developers")
    void shouldThrowExceptionWhenLessThan3Devs() throws InvalidAttributeException {

        SkillsByYearsOfExperience skillSet1 = new SkillsByYearsOfExperience();
        skillSet1.addNewSkill(Skill.PHP,Experience.EXPERT);

        Developer john = new Developer(new Email("johndoe@gmail.com"), skillSet1);

        List<Developer> developers= List.of(john);


        InvalidAttributeException e = assertThrows(InvalidAttributeException.class,() -> {
            Team team = new Team(projectNormal,developers);
            team.validateForProject();
        });

        String expectedError = "Team must have at least 3 developers";
        assertEquals(expectedError,e.getMessage());

    }

    @Test
    @DisplayName("should throw exception they are more than 8 developers")
    void shouldThrowExceptionWhenMoreThan8Devs() throws InvalidAttributeException {

        SkillsByYearsOfExperience skillSet1 = new SkillsByYearsOfExperience();
        skillSet1.addNewSkill(Skill.PHP,Experience.EXPERT);


        List<Developer> developers= List.of(
                new Developer(new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Email("asdf@gmail.com"), skillSet1),
                new Developer(new Email("f@gmail.com"), skillSet1),
                new Developer(new Email("vasd@gmail.com"), skillSet1),
                new Developer(new Email("eqwfv@gmail.com"), skillSet1),
                new Developer(new Email("ta@gmail.com"), skillSet1),
                new Developer(new Email("hsfd@gmail.com"), skillSet1),
                new Developer(new Email("jj@gmail.com"), skillSet1),
                new Developer(new Email("dfgh@gmail.com"), skillSet1)
        );


        InvalidAttributeException e = assertThrows(InvalidAttributeException.class,() -> {
            Team team = new Team(projectNormal,developers);
            team.validateForProject();
        });

        String expectedError = "Team can't have more than 8 developers";
        assertEquals(expectedError,e.getMessage());

    }

    @Test
    @DisplayName("should throw exception they are junior without expert")
    void shouldThrowExceptionWhenJuniorNoExpert() throws InvalidAttributeException {

        SkillsByYearsOfExperience skillSet1 = new SkillsByYearsOfExperience();
        skillSet1.addNewSkill(Skill.PHP,Experience.SKILLED);

        SkillsByYearsOfExperience skillSet2 = new SkillsByYearsOfExperience();
        skillSet2.addNewSkill(Skill.PHP,Experience.JUNIOR);


        List<Developer> developers= List.of(
                new Developer(new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Email("dfgh@gmail.com"), skillSet2),
                new Developer(new Email("ag@gmail.com"), skillSet2)
        );


        InvalidAttributeException e = assertThrows(InvalidAttributeException.class,() -> {
            Team team = new Team(projectNormal, developers);
            team.validateForProject();
        });

        String expectedError = "Team can't have a junior developer without an expert developer";
        assertEquals(expectedError,e.getMessage());

    }

    @Test
    @DisplayName("should throw exception they are no expert for 6 month project")
    void shouldThrowExceptionNoExpertFor6MonthProject() throws InvalidAttributeException {

        SkillsByYearsOfExperience skillSet1 = new SkillsByYearsOfExperience();
        skillSet1.addNewSkill(Skill.PHP,Experience.SKILLED);


        List<Developer> developers= List.of(
                new Developer(new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Email("dfgh@gmail.com"), skillSet1),
                new Developer(new Email("ag@gmail.com"), skillSet1)
        );

        InvalidAttributeException e = assertThrows(InvalidAttributeException.class,() -> {
            Team team = new Team(projectNormal,developers);
            team.validateForProject();
        });

        String expectedError = "Team need an expert if the project is longer than 6 months";
        assertEquals(expectedError,e.getMessage());

    }

    @Test
    @DisplayName("should throw exception when team is less than five with expert")
    void shouldThrowExceptionWhenTeamIsLessThanFiveWithExpert() throws InvalidAttributeException {

        SkillsByYearsOfExperience skillSet1 = new SkillsByYearsOfExperience();
        skillSet1.addNewSkill(Skill.PHP,Experience.SKILLED);

        SkillsByYearsOfExperience skillSet2 = new SkillsByYearsOfExperience();
        skillSet2.addNewSkill(Skill.PHP,Experience.EXPERT);


        List<Developer> developers= List.of(
                new Developer(new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Email("ggg@gmail.com"), skillSet1),
                new Developer(new Email("dfgh@gmail.com"), skillSet1),
                new Developer(new Email("ag@gmail.com"), skillSet2)
        );

        InvalidAttributeException e = assertThrows(InvalidAttributeException.class,() -> {
            Team team = new Team(projectNormal,developers);
            team.validateForProject();
        });

        String expectedError = "An expert cannot be in a team with less than 5 developers if the project is not critical";
        assertEquals(expectedError,e.getMessage());

    }

    @Test
    @DisplayName("should throw exception when team has more than 3 juniors")
    void shouldThrowExceptionWhenTeamContainMoreThan3Juniors() throws InvalidAttributeException {

        SkillsByYearsOfExperience skillSet1 = new SkillsByYearsOfExperience();
        skillSet1.addNewSkill(Skill.PHP,Experience.JUNIOR);

        SkillsByYearsOfExperience skillSet2 = new SkillsByYearsOfExperience();
        skillSet2.addNewSkill(Skill.PHP,Experience.EXPERT);


        List<Developer> developers= List.of(
                new Developer(new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Email("dfgh@gmail.com"), skillSet1),
                new Developer(new Email("gd@gmail.com"), skillSet1),
                new Developer(new Email("wef@gmail.com"), skillSet1),
                new Developer(new Email("ag@gmail.com"), skillSet2)
        );

        InvalidAttributeException e = assertThrows(InvalidAttributeException.class,() -> {
            Team team = new Team(projectNormal,developers);
            team.validateForProject();
        });

        String expectedError = "Team can't have more than 3 junior developers";
        assertEquals(expectedError,e.getMessage());

    }

    @Test
    @DisplayName("should throw exception when team has duplicates developers")
    void shouldThrowExceptionWhenTeamContainDuplicates() throws InvalidAttributeException {

        SkillsByYearsOfExperience skillSet1 = new SkillsByYearsOfExperience();
        skillSet1.addNewSkill(Skill.PHP,Experience.JUNIOR);

        SkillsByYearsOfExperience skillSet2 = new SkillsByYearsOfExperience();
        skillSet2.addNewSkill(Skill.PHP,Experience.EXPERT);


        List<Developer> developers= List.of(
                new Developer(new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Email("ag@gmail.com"), skillSet2)
        );

        InvalidAttributeException e = assertThrows(InvalidAttributeException.class,() -> {
            Team team = new Team(projectNormal,developers);
            team.validateForProject();
        });

        String expectedError = "There cannot be duplicates developers in the team";
        assertEquals(expectedError,e.getMessage());

    }

    @Test
    @DisplayName("Should add developer to team")
    void shouldAddDeveloperToTeam() throws InvalidAttributeException {

        SkillsByYearsOfExperience skillSet1 = new SkillsByYearsOfExperience();
        skillSet1.addNewSkill(Skill.PHP, Experience.JUNIOR);

        SkillsByYearsOfExperience skillSet2 = new SkillsByYearsOfExperience();
        skillSet2.addNewSkill(Skill.PHP, Experience.EXPERT);

        List<Developer> developers= List.of(
                new Developer(new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Email("johndoe2@gmail.com"), skillSet1),
                new Developer(new Email("ag@gmail.com"), skillSet2)
        );

        Team team = new Team(projectCritical,developers);
        team.addDeveloper(new Developer(new Email("adddev@email.com"), skillSet2));

        assertEquals(4,team.getDevelopers().size());
    }

    @Test
    @DisplayName("Should remove developer from team")
    void shouldRemoveDeveloperFromTeam() throws InvalidAttributeException {

        SkillsByYearsOfExperience skillSet1 = new SkillsByYearsOfExperience();
        skillSet1.addNewSkill(Skill.PHP, Experience.JUNIOR);

        SkillsByYearsOfExperience skillSet2 = new SkillsByYearsOfExperience();
        skillSet2.addNewSkill(Skill.PHP, Experience.EXPERT);

        Developer toRemove = new Developer(new Email("ag@gmail.com"), skillSet2);

        List<Developer> developers = List.of(
                new Developer(new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Email("johndoe2@gmail.com"), skillSet1),
                toRemove
        );

        Team team = new Team(projectCritical, developers);
        team.removeDeveloper(toRemove);

        assertEquals(2, team.getDevelopers().size());
    }

    @Test
    @DisplayName("Should throw exception when removing developer not in team")
    void shouldThrowExceptionWhenRemovingDeveloperNotInTeam() throws InvalidAttributeException {

        SkillsByYearsOfExperience skillSet1 = new SkillsByYearsOfExperience();
        skillSet1.addNewSkill(Skill.PHP, Experience.JUNIOR);

        SkillsByYearsOfExperience skillSet2 = new SkillsByYearsOfExperience();
        skillSet2.addNewSkill(Skill.PHP, Experience.EXPERT);

        Developer toRemove = new Developer(new Email("johndoe2@gmail.com"), skillSet1);

        List<Developer> developers = List.of(
                new Developer(new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Email("ag@gmail.com"), skillSet2),
                new Developer(new Email("ag2@gmail.com"), skillSet2)
        );

        Team team = new Team(projectCritical, developers);

        InvalidAttributeException e = assertThrows(InvalidAttributeException.class, () -> team.removeDeveloper(toRemove));

        String expectedError = "Developer does not exist in team";
        assertEquals(expectedError, e.getMessage());

    }


}