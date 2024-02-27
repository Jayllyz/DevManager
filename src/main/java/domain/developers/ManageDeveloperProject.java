package domain.developers;

import shared.exceptions.EntityAlreadyExistsException;
import shared.projects.Deadline;
import shared.projects.Name;
import shared.projects.SkillStack;
import shared.projects.StartDate;

import java.util.List;

public interface ManageDeveloperProject {

    List<Developer> getAvailableDevelopersForProject(StartDate start, Deadline deadline, SkillStack skillsNeeded) throws EntityAlreadyExistsException;

}
