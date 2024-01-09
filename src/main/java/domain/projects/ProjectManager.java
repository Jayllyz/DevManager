package domain.projects;

import domain.Skill;

import java.time.LocalDate;
import java.util.HashMap;

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
}
