package infrastructure.project.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import shared.Priority;
import shared.Skill;
import shared.Status;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class ProjectDTO {
    private String name;
    private Priority priority;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
    private HashMap<Skill, Integer> skillStack;
    private Status status;
    private List<DeveloperDTO> team;

    public ProjectDTO(String name, Priority priority, LocalDate start, LocalDate deadline, HashMap<Skill, Integer> stack, Status status, List<DeveloperDTO> team) {
        this.name = name;
        this.priority = priority;
        this.start = start;
        this.deadline = deadline;
        this.skillStack = stack;
        this.status = status;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public HashMap<Skill, Integer> getSkillStack() {
        return skillStack;
    }

    public Status getStatus() {
        return status;
    }

    public List<DeveloperDTO> getTeam() {
        return team;
    }
}
