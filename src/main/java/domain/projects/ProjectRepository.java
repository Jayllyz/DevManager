package domain.projects;

import domain.developers.Developer;
import shared.Status;
import shared.projects.Name;
import shared.projects.SkillStack;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository {
    Project createProject(Project project);
    List<Project> listProjectByStatus(Status status);
    Boolean existProject(Name name);
    List<Developer> getAvailableDevelopers(SkillStack skills);
    Boolean deleteProject(Project project);
    Project postponeProject(Project project, LocalDate startDate);
    Project getNextStartingProject();
}
