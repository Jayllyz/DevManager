package domain.projects;

import domain.Skill;
import infrastructure.project.ProjectFakeRepositoryAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ProjectTest {
    ProjectRepository projectRepository = new ProjectFakeRepositoryAdapter();

    @Test
    @DisplayName("Should create a project")
    void shouldCreateAProject() {
        HashMap<Skill, Integer> stack = new HashMap<>();
        stack.put(Skill.COBOL, 1);
        Project project = new Project("Project 1", 1, "Description", LocalDate.of(2024, 3, 1), LocalDate.of(2024, 5, 1), stack);
        assertInstanceOf(Project.class, project);
    }
}