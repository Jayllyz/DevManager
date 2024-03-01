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
    private HashMap<Skill, Integer> skillStack;
    private Status status;

    public ProjectDTO(String name, Priority priority, LocalDate start, LocalDate deadline, HashMap<Skill, Integer> stack, Status status) {
        this.name = name;
        this.priority = priority;
        this.start = start;
        this.deadline = deadline;
        this.skillStack = stack;
        this.status = status;
    }

    public ProjectDTO() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setSkillStack(HashMap<Skill, Integer> skillStack) {
        this.skillStack = skillStack;
    }

    public void setStatus(Status status) {
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

    public HashMap<Skill, Integer> getSkillStack() {
        return skillStack;
    }

    public Status getStatus() {
        return status;
    }
}
