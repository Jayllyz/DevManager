package domain.projects;

import shared.Skill;
import shared.Status;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface ManageProject {
    Project createProject(String name, int priority, String description, LocalDate start, LocalDate deadline, HashMap<Skill, Integer> stack);
    List<Project> listProjectByStatus(Status status);
    Boolean deleteProject(Project project);
    Project postponeProject(Project project, LocalDate startDate);
    Project getNextStartingProject();
}
