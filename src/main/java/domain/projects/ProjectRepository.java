package domain.projects;

import java.util.List;

public interface ProjectRepository {
    Project createProject(Project project);
    List<Project> listProjectByStatus(Status status);
    Boolean deleteProject(Project project);
}
