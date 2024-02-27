package infrastructure.developer.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import shared.Priority;
import shared.Skill;
import shared.Status;
import shared.projects.Deadline;
import shared.projects.Name;
import shared.projects.SkillStack;
import shared.projects.StartDate;

import java.time.LocalDate;
import java.util.HashMap;

public class ProjectDTO {
    private String name;
    private Priority priority;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
    private HashMap<Skill, Integer> stack;
    private Status status;

    public ProjectDTO(String name, Priority priority, LocalDate start, LocalDate deadline, HashMap<Skill, Integer> stack, Status status) {
        this.name = name;
        this.priority = priority;
        this.start = start;
        this.deadline = deadline;
        this.stack = stack;
        this.status = status;
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

    public HashMap<Skill, Integer> getStack() {
        return stack;
    }

    public Status getStatus() {
        return status;
    }
}
