package infrastructure.project;

import domain.Skill;
import domain.projects.Project;
import domain.projects.ProjectRepository;
import domain.projects.Status;

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
    List<Project> projects = List.of(
            new Project("Calculator", 1, "Une calculatrice en C", LocalDate.of(2024, 2, 6), LocalDate.of(2024, 4, 1), stack1),
            new Project("Project Network", 2, "Faire un double proxy en TLS avec Scratch", LocalDate.of(2024, 2, 2), LocalDate.of(2024, 4, 1), stack2),
            new Project("Annual Project", 3, "Refaire le projet annuel de 2022", LocalDate.of(2024, 2, 3), LocalDate.of(2024, 4, 1), stack3)
    );

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
    public Project postponeProject(Project project, LocalDate startDate) {
        if(startDate != null && startDate.isBefore(project.getStart())) {
            throw new IllegalArgumentException("startDate can't be before project start");
        }
        project.setStart(startDate);
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
