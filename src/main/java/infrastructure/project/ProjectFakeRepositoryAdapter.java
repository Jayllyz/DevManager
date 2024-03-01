package infrastructure.project;

import domain.projects.Project;
import domain.projects.ProjectRepository;
import domain.projects.Team;
import domain.projects.attributes.*;
import shared.Priority;
import shared.Status;
import shared.Skill;
import shared.exceptions.EntityNotFoundException;
import shared.projects.Deadline;
import shared.projects.Name;
import shared.exceptions.InvalidAttributeException;
import shared.projects.SkillStack;
import shared.projects.StartDate;

import java.time.LocalDate;
import java.util.*;

/**
 * The type Project fake repository adapter.
 */
public class ProjectFakeRepositoryAdapter implements ProjectRepository {
    List<Project> projects;
    {
        try {

            SkillStack skillStack1 = new SkillStack();
            SkillStack skillStack2 = new SkillStack();
            SkillStack skillStack3 = new SkillStack();

            skillStack1.put(Skill.C,4);

            skillStack2.put(Skill.SCRATCH,5);

            skillStack3.put(Skill.PHP, 2);
            skillStack3.put(Skill.COBOL, 4);
            skillStack3.put(Skill.COFFEE, 1);

            projects = new ArrayList<>(List.of(
                    new Project(new Name("Calculator"), Priority.NORMAL, new Description("Une calculatrice en C"), new StartDate(LocalDate.now().plusDays(1)), new Deadline(LocalDate.now().plusDays(20)), skillStack1,Status.CANCELLED),
                    new Project(new Name("Spotify"), Priority.CRITICAL, new Description("you know"), new StartDate(LocalDate.now().plusDays(1)), new Deadline(LocalDate.now().plusDays(20)), skillStack2,Status.IN_PROGRESS),
                    new Project(new Name("jsp"), Priority.NORMAL, new Description("un projet"), new StartDate(LocalDate.now().plusDays(1)), new Deadline(LocalDate.now().plusDays(20)), skillStack3,Status.DONE)
            ));
        } catch (InvalidAttributeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Project createProject(Project project) {
        projects.add(project);
        return project;
    }

    @Override
    public List<Project> listProjectByStatus(Status status) {
        List<Project> returnList = new ArrayList<>();
        for(Project project : projects) {
            if(project.getStatus() == status) {
                returnList.add(project);
            }
        }
        return returnList;
    }

    @Override
    public Boolean existProject(Name name) {
        for(Project project : projects) {
            String projectName = project.getName();
            if(projectName.equals(name.toString())) {
                return true;
            }
        }
        return false;
    }

    private Project getProject(Name name) throws EntityNotFoundException {
        for(Project project : projects) {
            String projectName = project.getName();
            if(projectName.equals(name.toString())) {
                return project;
            }
        }

        throw new EntityNotFoundException("Project not found");
    }

    @Override
    public Boolean deleteProject(Project project) {
        if(projects.contains(project)) {
            projects.remove(project);
            return true;
        }
        return false;
    }

    @Override
    public Project postponeProject(Project project, LocalDate startDate) {
        if(startDate != null && startDate.isBefore(project.getStart())) {
            throw new IllegalArgumentException("startDate can't be before project start");
        }

        if(startDate != null && startDate.isAfter(project.getDeadline())) {
            throw new IllegalArgumentException("startDate can't be after project deadline");
        }

        project.postponeProject(startDate);
        return project;
    }

    @Override
    public Project getNextStartingProject() {
        List<Project> projects = new ArrayList<>(this.projects);
        projects.sort(Comparator.comparing(Project::getStart));
        for(Project project : projects) {
            if(project.getStatus() == Status.WAITING) {
                return project;
            }
        }
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        return projects;
    }
}
