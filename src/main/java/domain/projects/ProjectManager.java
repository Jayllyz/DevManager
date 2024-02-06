package domain.projects;

import shared.Skill;
import domain.projects.attributes.Priority;
import domain.projects.attributes.Status;

import java.time.LocalDate;
import java.util.HashMap;
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
    public Project createProject(String name, Priority priority, String description, LocalDate start, LocalDate deadline, HashMap<Skill, Integer> stack) {
        Project project = new Project(name, priority, description, start, deadline, stack);
        if(project.getDeadline().isBefore(project.getStart())) {
            throw new IllegalArgumentException("Deadline cannot be before start date");
        }
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
