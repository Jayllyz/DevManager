package infrastructure.developer.DTO;

import domain.developers.Project;
import shared.Priority;
import shared.Skill;
import shared.Status;
import shared.projects.Deadline;
import shared.projects.Name;
import shared.projects.SkillStack;
import shared.projects.StartDate;

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

    public static Project mapDTOtoProject(ProjectDTO projectDTO) {
        String projectName = projectDTO.getName();
        LocalDate deadline = projectDTO.getDeadline();
        LocalDate start = projectDTO.getStart();
        Priority priority = projectDTO.getPriority();
        Status status = projectDTO.getStatus();
        HashMap<Skill, Integer> skillStack = projectDTO.getSkillStack();
        SkillStack skills = new SkillStack();

        for (HashMap.Entry<Skill, Integer> entry : skillStack.entrySet()) {
            Skill skill = entry.getKey();
            Integer devNumber = entry.getValue();
            skills.put(skill,devNumber);
        }


        return new Project(
                new Name(projectName),
                priority,
                new StartDate(start),
                new Deadline(deadline),
                skills,
                status
        );
    }
}
