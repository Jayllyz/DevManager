package infrastructure.project.DTO;

import domain.projects.Project;
import shared.Skill;
import shared.Status;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class ProjectMapper {

    public static ProjectDTO mapProjectToDTO(Project project, List<DeveloperDTO> team) {
        String name = project.getName();
        Status status = project.getStatus();
        String description = project.getDescription();
        LocalDate deadline = project.getDeadline();
        LocalDate start = project.getStart();
        String priority = project.getPriority().toString();
        HashMap<Skill, Integer> skills = project.getStack();

        return new ProjectDTO(
                name,
                priority,
                description,
                start,
                deadline,
                skills,
                status,
                team
        );
    }
}
