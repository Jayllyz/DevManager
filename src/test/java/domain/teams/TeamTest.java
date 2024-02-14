package domain.teams;

import domain.developers.Developer;
import infrastructure.team.TeamFakeRepositoryAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shared.Experience;
import shared.Priority;
import shared.Skill;
import shared.developers.Email;
import shared.developers.Name;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.EntityNotFoundException;
import shared.exceptions.InvalidAttributeException;
import shared.projects.Deadline;
import shared.projects.SkillStack;
import shared.projects.StartDate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    private final TeamManager teamManager;

    Project projectNormal;

    public TeamTest() throws InvalidAttributeException {
        TeamRepository repository = new TeamFakeRepositoryAdapter();
        this.teamManager = new TeamManager(repository);

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate in7Months = LocalDate.now().plusMonths(7);

        SkillStack skillsNeeded = new SkillStack();
        skillsNeeded.put(Skill.SCRATCH,1);
        skillsNeeded.put(Skill.HTML,3);

        this.projectNormal = new Project(Priority.NORMAL,new StartDate(tomorrow),new Deadline(in7Months),skillsNeeded);
    }

    @Test
    @DisplayName("should throw exception they are less than 3 developers")
    void shouldThrowExceptionWhenLessThan3Devs() throws InvalidAttributeException {

        SkillsByYearsOfExperience skillSet1 = new SkillsByYearsOfExperience();
        skillSet1.addNewSkill(Skill.PHP,Experience.EXPERT);

        Developer john = new Developer(new Name("john"), new Name("Doe"), new Email("johndoe@gmail.com"), skillSet1);

        List<Developer> developers= List.of(john);


        InvalidAttributeException e = assertThrows(InvalidAttributeException.class,() -> {
            Team team = new Team(projectNormal,developers);
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
                new Developer(new Name("john"), new Name("Doe"), new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("asdf@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("f@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("vasd@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("eqwfv@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("ta@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("hsfd@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("jj@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("dfgh@gmail.com"), skillSet1)
        );


        InvalidAttributeException e = assertThrows(InvalidAttributeException.class,() -> {
            Team team = new Team(projectNormal,developers);
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
                new Developer(new Name("john"), new Name("Doe"), new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("dfgh@gmail.com"), skillSet2),
                new Developer(new Name("john"), new Name("Doe"), new Email("ag@gmail.com"), skillSet2)
        );


        InvalidAttributeException e = assertThrows(InvalidAttributeException.class,() -> {
            Team team = new Team(projectNormal,developers);
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
                new Developer(new Name("john"), new Name("Doe"), new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("dfgh@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("ag@gmail.com"), skillSet1)
        );

        InvalidAttributeException e = assertThrows(InvalidAttributeException.class,() -> {
            Team team = new Team(projectNormal,developers);
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
                new Developer(new Name("john"), new Name("Doe"), new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("dfgh@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("dfgh@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("ag@gmail.com"), skillSet2)
        );

        InvalidAttributeException e = assertThrows(InvalidAttributeException.class,() -> {
            Team team = new Team(projectNormal,developers);
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
                new Developer(new Name("john"), new Name("Doe"), new Email("johndoe@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("dfgh@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("gd@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("wef@gmail.com"), skillSet1),
                new Developer(new Name("john"), new Name("Doe"), new Email("ag@gmail.com"), skillSet2)
        );

        InvalidAttributeException e = assertThrows(InvalidAttributeException.class,() -> {
            Team team = new Team(projectNormal,developers);
        });

        String expectedError = "Team can't have more than 3 junior developers";
        assertEquals(expectedError,e.getMessage());

    }

//    @Test
//    @DisplayName("Should create a team")
//    void shouldCreateATeam() {
//        HashMap<Skill, Experience> skillSet1 = new HashMap<>();
//        HashMap<Skill, Experience> skillSet2 = new HashMap<>();
//        HashMap<Skill, Experience> skillSet3 = new HashMap<>();
//
//        try {
//            skillSet1.put(Skill.HTML, Experience.fromYearsOfExperience(4));
//            skillSet2.put(Skill.SCRATCH, Experience.fromYearsOfExperience(4));
//            skillSet3.put(Skill.COBOL, Experience.fromYearsOfExperience(4));
//        } catch (InvalidAttributeException e) {
//            throw new IllegalArgumentException(e.getMessage());
//        }
//
//        Developer developer1;
//        Developer developer2;
//        Developer developer3;
//        try {
//            developer1 = new Developer(new shared.developers.Name("john"), new shared.developers.Name("Doe"), new Email("johndoe@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
//
//            developer2 = new Developer(new shared.developers.Name("Marc"), new shared.developers.Name("Robel"), new Email("marc@gmail.com"), new SkillsByYearsOfExperience(skillSet2));
//            developer3 = new Developer(new shared.developers.Name("Jeanne"), new shared.developers.Name("Darc"), new Email("jeanne@gmail.com"), new SkillsByYearsOfExperience(skillSet3));
//        } catch (InvalidAttributeException e) {
//            throw new IllegalArgumentException(e.getMessage());
//        }
//
//        List<Developer> listDeveloper = List.of(
//                developer1,
//                developer2,
//                developer3
//        );
//
//        Team team;
//        try {
//            team = new Team(projectNormal, listDeveloper);
//        } catch (InvalidAttributeException e) {
//            throw new IllegalArgumentException(e.getMessage());
//        }
//        assertInstanceOf(Team.class, team);
//        assertNotNull(team);
//    }
//
//    @Test
//    @DisplayName("Should create a team with the function")
//    void shouldCreateATeamWithTheFunction() throws InvalidAttributeException {
//        HashMap<Skill, Experience> skillSet1 = new HashMap<>();
//        HashMap<Skill, Experience> skillSet2 = new HashMap<>();
//        HashMap<Skill, Experience> skillSet3 = new HashMap<>();
//        try {
//            skillSet1.put(Skill.HTML, Experience.fromYearsOfExperience(4));
//            skillSet2.put(Skill.SCRATCH, Experience.fromYearsOfExperience(4));
//            skillSet3.put(Skill.COBOL, Experience.fromYearsOfExperience(4));
//        } catch (InvalidAttributeException e) {
//            throw new IllegalArgumentException(e.getMessage());
//        }
//
//        Developer developer1;
//        Developer developer2;
//        Developer developer3;
//        try {
//            developer1 = new Developer(new shared.developers.Name("Tyrion"), new shared.developers.Name("Fordring"), new Email("tf@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
//            developer2 = new Developer(new shared.developers.Name("Onyxia"), new shared.developers.Name("AileNoir"), new Email("oa@gmail.com"), new SkillsByYearsOfExperience(skillSet2));
//            developer3 = new Developer(new shared.developers.Name("Nefarian"), new shared.developers.Name("AileNoir"), new Email("na@gmail.com"), new SkillsByYearsOfExperience(skillSet3));
//        } catch (InvalidAttributeException e) {
//            throw new IllegalArgumentException(e.getMessage());
//        }
//
//        List<Developer> listDeveloper = List.of(
//                developer1,
//                developer2,
//                developer3
//        );
//
//        Team team = teamManager.createTeam(new Name("Test"), new Developers(listDeveloper));
//        assertInstanceOf(Team.class, team);
//    }
}