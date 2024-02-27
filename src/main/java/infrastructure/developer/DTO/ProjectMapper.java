package infrastructure.developer.DTO;

import domain.developers.Project;
import shared.Priority;
import shared.Skill;
import shared.Status;

import java.time.LocalDate;
import java.util.HashMap;

public class ProjectMapper {

    public static ProjectDTO mapProjectToDTO(Project project) {
        String name = project.getName();
        Status status = project.getStatus();
        LocalDate deadline = project.getDeadline();
        LocalDate start = project.getStart();
        Priority priority = project.getPriority();
        HashMap<Skill, Integer> skills = project.getStack();

        return new ProjectDTO(name,priority,start,deadline,skills,status);
    }
}
