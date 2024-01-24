package domain.projects;

import domain.Skill;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface ManageProject {
    Project createProject(String name, int priority, String description, LocalDate start, LocalDate deadline, HashMap<Skill, Integer> stack);
    List<Project> listProjectByStatus(Status status);
    Boolean deleteProject(Project project);
}
