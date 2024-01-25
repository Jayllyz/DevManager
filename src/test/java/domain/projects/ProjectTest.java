package domain.projects;

import domain.Skill;
import domain.projects.attributes.Priority;
import domain.projects.attributes.Status;
import infrastructure.project.ProjectFakeRepositoryAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {
    ProjectRepository projectRepository = new ProjectFakeRepositoryAdapter();

    @Test
    @DisplayName("Should create a project")
    void shouldCreateAProject() {
        HashMap<Skill, Integer> stack = new HashMap<>();
        stack.put(Skill.COBOL, 1);
        Project project = new Project("Project 1", Priority.NORMAL, "Description", LocalDate.of(2024, 3, 1), LocalDate.of(2024, 5, 1), stack);
        assertInstanceOf(Project.class, project);
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
        stack.put(Skill.COBOL, 1);
        Project project = new Project("Project 1", Priority.NORMAL, "Description", LocalDate.of(2024, 3, 1), LocalDate.of(2024, 5, 1), stack);
        assertThrows(IllegalArgumentException.class, () -> projectRepository.postponeProject(project, LocalDate.of(2024, 2, 1)));
    }

    @Test
    @DisplayName("Postpone project should return a project")
    void postponeProjectShouldReturnAProject() {
        HashMap<Skill, Integer> stack = new HashMap<>();
        stack.put(Skill.COBOL, 1);
        Project project = new Project("Project 1", Priority.NORMAL, "Description", LocalDate.of(2024, 3, 1), LocalDate.of(2024, 5, 1), stack);
        assertInstanceOf(Project.class, projectRepository.postponeProject(project, LocalDate.of(2024, 4, 1)));
    }

    @Test
    @DisplayName("Should return the next starting project")
    void shouldReturnTheNextStartingProject() {
        assertNotNull(projectRepository.getNextStartingProject());
        assertInstanceOf(Project.class, projectRepository.getNextStartingProject());
        assertEquals("Project Network", projectRepository.getNextStartingProject().getName());
    }

}
