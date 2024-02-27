package domain.projects;

import shared.Priority;
import domain.projects.attributes.*;
import shared.Status;
import shared.exceptions.EntityAlreadyExistsException;
import shared.projects.*;

import java.time.LocalDate;
import java.util.List;

public class ProjectManager implements ManageProject {
    ProjectRepository repository;

    public ProjectManager(ProjectRepository repository) {

        if(repository == null) {
            throw new IllegalArgumentException("repository can't be null");
        }

        this.repository = repository;
    }

    @Override
    public Project createProject(Name name, Priority priority, Description description, StartDate start, Deadline deadline, SkillStack skillStack) throws EntityAlreadyExistsException {

        if(this.repository.existProject(name)){
            throw new EntityAlreadyExistsException("Project with name " + name + " already exist.");
        }

        Project project = new Project(name, priority, description, start, deadline, skillStack, Status.WAITING);

        return repository.createProject(project);
    }

    @Override
    public List<Developer> getAvailableDevelopersForProject(Name name) throws EntityAlreadyExistsException {
        if(!this.repository.existProject(name)){
            throw new EntityAlreadyExistsException("No project found");
        }

        return null;

    }

    @Override
    public List<Project> listProjectByStatus(Status status) {
        return repository.listProjectByStatus(status);
    }

    @Override
    public Boolean deleteProject(Project project) {
        if(project.getStatus() != Status.WAITING) {
            throw new IllegalArgumentException("Project is not in waiting status");
        }
        repository.deleteProject(project);
        return true;
    }

  
    @Override
    public Project postponeProject(Project project, LocalDate startDate) {
        return repository.postponeProject(project, startDate);
    }

    @Override
    public Project getNextStartingProject() {
        return repository.getNextStartingProject();
    }
}
