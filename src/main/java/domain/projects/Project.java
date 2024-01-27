package domain.projects;


import shared.Skill;

import java.time.LocalDate;
import java.util.HashMap;

public class Project {
    private String name;
    private int priority;
    private String description;
    private LocalDate start;
    private LocalDate deadline;
    private HashMap<Skill, Integer> stack;
    private Status status;

    public Project(String name, int priority, String description, LocalDate start, LocalDate deadline, HashMap<Skill, Integer> stack) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.start = start;
        this.deadline = deadline;
        this.stack = stack;
        this.status = Status.WAITING;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public HashMap<Skill, Integer> getStack() {
        return stack;
    }

    public void setStack(HashMap<Skill, Integer> stack) {
        this.stack = stack;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
