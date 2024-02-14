package domain.projects;

import shared.Priority;
import shared.Skill;
import domain.projects.attributes.*;
import shared.Status;
import shared.exceptions.InvalidAttributeException;
import shared.projects.*;

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
        Project project;
        try {
            project = new Project(new Name(name), Priority.intToPriority(priority), new Description(description), new Start(start), new Deadline(deadline), new SkillStack(stack));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

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
