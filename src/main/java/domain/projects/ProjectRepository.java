package domain.projects;

import shared.Status;
import shared.projects.Name;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository {
    Project createProject(Project project);
    List<Project> listProjectByStatus(Status status);
    Boolean existProject(Name name);
    Boolean deleteProject(Project project);
    Project postponeProject(Project project, LocalDate startDate);
    Project getNextStartingProject();
    List<Project> getAllProjects();
    Project getProjectByName(Name name);
}
