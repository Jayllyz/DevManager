package domain.projects;

import shared.Priority;
import domain.projects.attributes.*;
import shared.Status;
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
    public Project createProject(Name name, Priority priority, Description description, StartDate start, Deadline deadline, SkillStack skillStack) {

        Project project = new Project(name, priority, description, start, deadline, skillStack);

        // step1 : find availables developers for project

        // step2 : createTeam

        repository.createProject(project);
        return project;
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
