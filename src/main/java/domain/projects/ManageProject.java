package domain.projects;

import domain.Skill;
import domain.projects.attributes.Priority;
import domain.projects.attributes.Status;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface ManageProject {
    Project createProject(String name, Priority priority, String description, LocalDate start, LocalDate deadline, HashMap<Skill, Integer> stack);
    List<Project> listProjectByStatus(Status status);
    Boolean deleteProject(Project project);
    Project postponeProject(Project project, LocalDate startDate);
    Project getNextStartingProject();
}
