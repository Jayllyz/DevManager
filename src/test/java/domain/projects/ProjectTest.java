package domain.projects;

import domain.developers.DeveloperManager;
import domain.developers.DeveloperRepository;
import domain.developers.ManageDeveloperProject;
import domain.projects.attributes.*;
import infrastructure.developer.driven.DeveloperFakeRepositoryAdapter;
import domain.teams.ManageTeamProject;
import domain.teams.TeamManager;
import domain.teams.TeamRepository;
import infrastructure.project.DeveloperGateway;
import infrastructure.project.TeamGateway;
import infrastructure.team.TeamFakeRepositoryAdapter;
import shared.Experience;
import shared.Priority;
import shared.Skill;
import infrastructure.project.ProjectFakeRepositoryAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shared.Status;
import shared.developers.Email;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.EntityAlreadyExistsException;
import shared.exceptions.EntityNotFoundException;
import shared.exceptions.InvalidAttributeException;
import shared.projects.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {
    ProjectRepository projectRepository;
    ProjectManager projectManager;
    DeveloperManagement developerManagement;
    TeamManagement teamManagement;

    public ProjectTest() throws InvalidAttributeException {
        projectRepository = new ProjectFakeRepositoryAdapter();

        DeveloperRepository developerRepository = new DeveloperFakeRepositoryAdapter();
        ManageDeveloperProject developerManager = new DeveloperManager(developerRepository);
        TeamRepository teamRepository = new TeamFakeRepositoryAdapter();
        ManageTeamProject teamManager = new TeamManager(teamRepository);

        developerManagement = new DeveloperGateway(developerManager);
        teamManagement = new TeamGateway(teamManager);

        projectManager = new ProjectManager(projectRepository, developerManagement, teamManagement);

    }

    @Test
    @DisplayName("Should create a project")
    void shouldCreateAProject() throws InvalidAttributeException, EntityAlreadyExistsException {

        SkillStack skillsNeeded = new SkillStack();
        skillsNeeded.put(Skill.JAVA,5);
        skillsNeeded.put(Skill.JAVA,3);

        LocalDate k = LocalDate.now();

        Project result = projectManager.createProject(
                new Name("refonte site"),
                Priority.NORMAL,
                new Description("test"),
                new StartDate(LocalDate.now().plusDays(1)),
                new Deadline(LocalDate.now().plusDays(10)),
                skillsNeeded
        );

        assertInstanceOf(Project.class,result);
    }

    @Test
    @DisplayName("Can't create a project when start date is after end date")
    void shouldThrowExceptionWhenStartDateAfterDeadline() throws InvalidAttributeException {

        SkillStack skillsNeeded = new SkillStack();
        skillsNeeded.put(Skill.JAVA,5);
        skillsNeeded.put(Skill.JAVA,3);

        LocalDate k = LocalDate.now();


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        Project result = projectManager.createProject(
                new Name("refonte site"),
                Priority.NORMAL,
                new Description("test"),
                new StartDate(LocalDate.now().plusDays(100)),
                new Deadline(LocalDate.now().plusDays(10)),
                skillsNeeded
        );
        });

        String message = "Start date of the project cannot be after end date !";

        assertEquals(message,exception.getMessage());
    }

    @Test
    @DisplayName("Can't create a project with already existing name")
    void shouldThrowExceptionWhenProjectNameIsTaken() throws InvalidAttributeException {

        SkillStack skillsNeeded = new SkillStack();
        skillsNeeded.put(Skill.JAVA,5);
        skillsNeeded.put(Skill.JAVA,3);

        LocalDate k = LocalDate.now();

        EntityAlreadyExistsException exception = assertThrows(EntityAlreadyExistsException.class, () -> {
            Project result = projectManager.createProject(
                    new Name("Spotify"),
                    Priority.NORMAL,
                    new Description("test"),
                    new StartDate(LocalDate.now().plusDays(1)),
                    new Deadline(LocalDate.now().plusDays(10)),
                    skillsNeeded
            );
        });

        String message = "Project with name Spotify already exist.";

        assertEquals(message,exception.getMessage());
    }

    @Test
    @DisplayName("Project should be 5 month durations")
    void shouldReturn5MonthsForDurationInMonth() throws InvalidAttributeException, EntityAlreadyExistsException {

        SkillStack skillsNeeded = new SkillStack();
        skillsNeeded.put(Skill.JAVA,5);
        skillsNeeded.put(Skill.JAVA,3);

        LocalDate k = LocalDate.now();

        Project result = projectManager.createProject(
                new Name("refonte site"),
                Priority.NORMAL,
                new Description("test"),
                new StartDate(LocalDate.now().plusDays(1)),
                new Deadline(LocalDate.now().plusMonths(4).plusDays(40)),
                skillsNeeded
        );

        assertEquals(5,result.getDurationInMonth());

    }

    @Test
    @DisplayName("should get all developers available for project")
    void shouldGetAllAvailableDevelopers() throws InvalidAttributeException, EntityAlreadyExistsException, EntityNotFoundException {

        SkillStack skillsNeeded = new SkillStack();
        skillsNeeded.put(Skill.JAVA,5);
        skillsNeeded.put(Skill.PHP,3);

        LocalDate k = LocalDate.now();

        List<Developer> availableDevelopers = projectManager.getAvailableDevelopersForProject(new Name("Calculator"));

        assertEquals(2,availableDevelopers.size());

    }


    @Test
    @DisplayName("Should return list of WAITING projects")
    void shouldReturnListOfWaitingProjects() {
        assertNotNull(projectRepository.listProjectByStatus(Status.WAITING));
        assertInstanceOf(List.class, projectRepository.listProjectByStatus(Status.WAITING));
    }

    @Test
    @DisplayName("Should delete a waiting project")
    void shouldDeleteAProject() {
        Project project = projectRepository.listProjectByStatus(Status.WAITING).get(0);
        assertTrue(projectRepository.deleteProject(project));
    }
  
    @Test
    @DisplayName("Postpone project should throw IllegalArgumentException if startDate is before project start")
    void postponeProjectShouldFail() {
        HashMap<Skill, Integer> stack = new HashMap<>();
        stack.put(Skill.COBOL, 4);
        Project project;

        try{
            project = new Project(new Name("Project 1"), Priority.NORMAL, new Description("Description"), new StartDate(LocalDate.of(2024, 3, 1)), new Deadline(LocalDate.of(2024, 5, 1)), new SkillStack(), Status.WAITING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThrows(IllegalArgumentException.class, () -> projectRepository.postponeProject(project, LocalDate.of(2024, 2, 1)));
    }

    @Test
    @DisplayName("Postpone project should return a project")
    void postponeProjectShouldReturnAProject() {
        HashMap<Skill, Integer> stack = new HashMap<>();
        stack.put(Skill.COBOL, 4);
        Project project;
        try{
            project = new Project(new Name("Project 1"), Priority.NORMAL, new Description("Description"), new StartDate(LocalDate.of(2024, 3, 1)), new Deadline(LocalDate.of(2024, 5, 1)), new SkillStack(), Status.WAITING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertInstanceOf(Project.class, projectRepository.postponeProject(project, LocalDate.of(2024, 4, 1)));
    }

    @Test
    @DisplayName("Postpone project should throw IllegalArgumentException if startDate is before initial project start")
    void postponeProjectShouldFailIfStartDateIsBeforeInitialProjectStart() {
        HashMap<Skill, Integer> stack = new HashMap<>();
        stack.put(Skill.COBOL, 4);
        Project project;
        try{
            project = new Project(new Name("Project 1"), Priority.NORMAL, new Description("Description"), new StartDate(LocalDate.of(2024, 3, 1)), new Deadline(LocalDate.of(2024, 5, 1)), new SkillStack(), Status.WAITING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThrows(IllegalArgumentException.class, () -> projectRepository.postponeProject(project, LocalDate.of(2024, 2, 1)));
    }

    @Test
    @DisplayName("Postpone project should throw IllegalArgumentException if startDate is after the deadline")
    void postponeProjectShouldFailIfStartDateIsAfterInitialProjectDeadline() {
        HashMap<Skill, Integer> stack = new HashMap<>();
        stack.put(Skill.COBOL, 4);
        Project project;
        try{
            project = new Project(new Name("Project 1"), Priority.NORMAL, new Description("Description"), new StartDate(LocalDate.of(2024, 3, 1)), new Deadline(LocalDate.of(2024, 5, 1)), new SkillStack(), Status.WAITING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThrows(IllegalArgumentException.class, () -> projectRepository.postponeProject(project, LocalDate.of(2024, 6, 1)));
    }

    @Test
    @DisplayName("Should return the next starting project")
    void shouldReturnTheNextStartingProject() {
        assertNotNull(projectRepository.getNextStartingProject());
        assertInstanceOf(Project.class, projectRepository.getNextStartingProject());
        assertEquals("Calculator", projectRepository.getNextStartingProject().getName());
    }

    @Test
    @DisplayName("Should return a project with method getNextStartingProject with Status WAITING")
    void shouldReturnAProjectWithMethodGetNextStartingProjectWithStatusWaiting() {
        Project project = projectRepository.getNextStartingProject();
        assertEquals(Status.WAITING, project.getStatus());
    }

    @Test
    @DisplayName("Should return an Email with getEmail")
    void shouldReturnAnEmailWithGetEmail() {
        domain.projects.Developer developer = new domain.projects.Developer(new shared.developers.Email("johndoe@gmail.com"), new shared.developers.SkillsByYearsOfExperience(new HashMap<>()));
        assertInstanceOf(Email.class, developer.getEmail());
    }

    @Test
    @DisplayName("Should return a String with getEmailAddress")
    void shouldReturnAStringWithGetEmailAddress() {
        domain.projects.Developer developer = new domain.projects.Developer(new shared.developers.Email("johndoe@gmail.com"), new shared.developers.SkillsByYearsOfExperience(new HashMap<>()));
        assertInstanceOf(String.class, developer.getEmailAddress());
    }

    @Test
    @DisplayName("Should return a List of Skill with getSkills")
    void shouldReturnAListOfSkillWithGetSkills() {
        HashMap<Skill, Experience> hashMap = new HashMap<>();
        hashMap.put(Skill.COBOL, Experience.fromYearsOfExperience(14));
        SkillsByYearsOfExperience skills = new SkillsByYearsOfExperience(hashMap);
        domain.projects.Developer developer = new domain.projects.Developer(new shared.developers.Email("johndoe@gmail.com"), skills);
        assertInstanceOf(List.class, developer.getSkills());
    }

    @Test
    @DisplayName("Should return a boolean with hasSkill")
    void shouldReturnABooleanWithHasSkill() {
        HashMap<Skill, Experience> hashMap = new HashMap<>();
        hashMap.put(Skill.COBOL, Experience.fromYearsOfExperience(14));
        SkillsByYearsOfExperience skills = new SkillsByYearsOfExperience(hashMap);
        domain.projects.Developer developer = new domain.projects.Developer(new shared.developers.Email("johndoe@gmail.com"), skills);
        assertInstanceOf(Boolean.class, developer.hasSkill(Skill.COBOL));
    }

    @Test
    @DisplayName("Should return an Experience with getSkillExperience")
    void shouldReturnAnExperienceWithGetSkillExperience() {
        HashMap<Skill, Experience> hashMap = new HashMap<>();
        hashMap.put(Skill.COBOL, Experience.fromYearsOfExperience(14));
        SkillsByYearsOfExperience skills = new SkillsByYearsOfExperience(hashMap);
        domain.projects.Developer developer = new domain.projects.Developer(new shared.developers.Email("johndoe@gmail.com"), skills);
        assertInstanceOf(Experience.class, developer.getSkillExperience(Skill.COBOL));
    }

    @Test
    @DisplayName("Should return an Experience with getGlobalExperience")
    void shouldReturnAnExperienceWithGetGlobalExperience() {
        HashMap<Skill, Experience> hashMap = new HashMap<>();
        hashMap.put(Skill.COBOL, Experience.fromYearsOfExperience(14));
        SkillsByYearsOfExperience skills = new SkillsByYearsOfExperience(hashMap);
        domain.projects.Developer developer = new domain.projects.Developer(new shared.developers.Email("johndoe@gmail.com"), skills);
        assertInstanceOf(Experience.class, developer.getGlobalExperience());
    }

    @Test
    @DisplayName("Should return a String with getFirstName")
    void shouldReturnAStringWithGetFirstName() {
        domain.projects.Developer developer = new domain.projects.Developer(new shared.developers.Email("johndoe@gmail.com"), new shared.developers.SkillsByYearsOfExperience(new HashMap<>()));
        assertInstanceOf(String.class, developer.getEmailAddress());
    }

    @Test
    @DisplayName("Should return SkillsByYearsOfExperience with getSkillsByYearsOfExperience with getSkillsByYearsOfExperience")
    void shouldReturnSkillsByYearsOfExperienceWithGetSkillsByYearsOfExperienceWithGetSkillsByYearsOfExperience() {
        domain.projects.Developer developer = new domain.projects.Developer(new shared.developers.Email("johndoe@gmail.com"), new shared.developers.SkillsByYearsOfExperience(new HashMap<>()));
        assertInstanceOf(SkillsByYearsOfExperience.class, developer.getSkillsByYearsOfExperience());
    }

    @Test
    @DisplayName("Should return a List of Project with getAllProjects")
    void shouldReturnAListOfProjectWithGetAllProjects() {
        assertInstanceOf(List.class, projectRepository.getAllProjects());
    }

    @Test
    @DisplayName("Should return a Project with getProjectByName")
    void shouldReturnAProjectWithGetProjectByName() throws EntityNotFoundException {
        assertNotNull(projectRepository.getProjectByName(new Name("Calculator")));
        assertInstanceOf(Project.class, projectRepository.getProjectByName(new Name("Calculator")));
    }

    @Test
    @DisplayName("Should return an EntityNotFoundException with getProjectByName")
    void shouldReturnAnEntityNotFoundExceptionWithGetProjectByName() {
        assertThrows(EntityNotFoundException.class, () -> projectRepository.getProjectByName(new Name("Project 1")));
    }

    @Test
    @DisplayName("Should return a list of Project with listProjectByStatus")
    void shouldReturnAListOfProjectWithListProjectByStatus() {
        assertInstanceOf(List.class, projectRepository.listProjectByStatus(Status.WAITING));
    }

    @Test
    @DisplayName("Should return a Boolean with deleteProject")
    void shouldReturnABooleanWithDeleteProject() {
        Project project = projectRepository.listProjectByStatus(Status.WAITING).get(0);
        assertInstanceOf(Boolean.class, projectRepository.deleteProject(project));
    }

    @Test
    @DisplayName("Should return a Project with postponeProject")
    void shouldReturnAProjectWithPostponeProject() {
        HashMap<Skill, Integer> stack = new HashMap<>();
        stack.put(Skill.COBOL, 4);
        Project project;
        try{
            project = new Project(new Name("Project 1"), Priority.NORMAL, new Description("Description"), new StartDate(LocalDate.of(2024, 3, 1)), new Deadline(LocalDate.of(2024, 5, 1)), new SkillStack(), Status.WAITING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        project = projectRepository.postponeProject(project, LocalDate.of(2024, 4, 1));
        LocalDate newstartDate = LocalDate.of(2024, 4, 1);
        assertEquals(newstartDate, project.getStart());
    }
}
