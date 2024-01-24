package domain.projects;

import javax.sound.sampled.Port;
import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository {
    Project createProject(Project project);

    List<Project> listProjectByStatus(Status status);

    Project postponeProject(Project project, LocalDate startDate);

    Project getNextStartingProject();
}
