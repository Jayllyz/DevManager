package infrastructure.gateway;

import domain.developers.Developer;
import domain.developers.ManageDeveloperProject;
import shared.exceptions.EntityAlreadyExistsException;
import shared.projects.Deadline;
import shared.projects.Name;
import shared.projects.SkillStack;
import shared.projects.StartDate;

import java.util.List;

public class ProjectManageDeveloper implements ManageDeveloperProject {

    @Override
    public List<Developer> getAvailableDevelopersForProject(StartDate start, Deadline deadline, SkillStack skillsNeeded) throws EntityAlreadyExistsException {
        return null;
    }
}
