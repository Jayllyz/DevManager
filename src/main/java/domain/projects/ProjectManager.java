package domain.projects;

import domain.Skill;

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
    public Project createProject(String name, int priority, String description, LocalDate start, LocalDate deadline, HashMap<Skill, Integer> stack) {
        Project project = new Project(name, priority, description, start, deadline, stack);
        repository.createProject(project);
        return project;
    }

    @Override
    public List<Project> listProjectByStatus(Status status) {
        return repository.listProjectByStatus(status);
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
