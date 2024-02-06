package infrastructure.project;

import domain.projects.Project;
import domain.projects.ProjectRepository;
import domain.projects.attributes.*;
import shared.Priority;
import shared.Status;
import shared.Skill;
import shared.projects.Name;
import shared.exceptions.InvalidAttributeException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * The type Project fake repository adapter.
 */
public class ProjectFakeRepositoryAdapter implements ProjectRepository {

    /**
     * The Stack 1.
     */
    HashMap<Skill, Integer> stack1 = new HashMap<>();
    /**
     * The Stack 2.
     */
    HashMap<Skill, Integer> stack2 = new HashMap<>();
    /**
     * The Stack 3.
     */
    HashMap<Skill, Integer> stack3 = new HashMap<>();

    /**
     * The Projects.
     */
    List<Project> projects;
    {
        try {
            projects = new ArrayList<>(List.of(
                    new Project(new Name("Calculator"), Priority.NORMAL, new Description("Une calculatrice en C"), new Start(LocalDate.of(2024, 2, 6)), new Deadline(LocalDate.of(2024, 4, 1)), new SkillStack(stack1)),
                    new Project(new Name("Project Network"), Priority.BEST_EFFORT, new Description("Faire un double proxy en TLS avec Scratch"), new Start(LocalDate.of(2024, 2, 2)), new Deadline(LocalDate.of(2024, 4, 1)), new SkillStack(stack2)),
                    new Project(new Name("Annual Project"), Priority.CRITICAL, new Description("Refaire le projet annuel de 2022"), new Start(LocalDate.of(2024, 2, 3)), new Deadline(LocalDate.of(2024, 4, 1)), new SkillStack(stack3))
            ));
        } catch (InvalidAttributeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Instantiates a new Project fake repository adapter.
     */
    public ProjectFakeRepositoryAdapter() {
        stack1.put(Skill.C, 3);

        stack2.put(Skill.SCRATCH, 5);

        stack3.put(Skill.PHP, 2);
        stack3.put(Skill.COBOL, 4);
        stack3.put(Skill.COFFEE, 1);
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


}
