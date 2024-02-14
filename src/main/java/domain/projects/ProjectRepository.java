package domain.projects;

import shared.Status;
import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository {
    Project createProject(Project project);
    List<Project> listProjectByStatus(Status status);
    Boolean deleteProject(Project project);
    Project postponeProject(Project project, LocalDate startDate);
    Project getNextStartingProject();
}
