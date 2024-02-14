package domain.projects;

import shared.projects.Deadline;
import domain.projects.attributes.Description;
import shared.projects.SkillStack;
import shared.projects.StartDate;
import shared.Priority;
import shared.Status;
import shared.projects.Name;

import java.time.LocalDate;
import java.util.List;

public interface ManageProject {
    Project createProject(Name name, Priority priority, Description description, StartDate start, Deadline deadline, SkillStack skillStack);
    List<Project> listProjectByStatus(Status status);
    Boolean deleteProject(Project project);
    Project postponeProject(Project project, LocalDate startDate);
    Project getNextStartingProject();
}
