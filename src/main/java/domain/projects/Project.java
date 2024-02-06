package domain.projects;

import shared.Priority;
import shared.Skill;
import domain.projects.attributes.*;
import shared.Status;
import shared.projects.*;
import shared.exceptions.InvalidAttributeException;

import java.time.LocalDate;
import java.util.HashMap;

public class Project {
    private Name name;
    private Priority priority;
    private Description description;
    private Start start;
    private Deadline deadline;
    private SkillStack stack;
    private Status status;

    public Project(Name name, Priority priority, Description description, Start start, Deadline deadline, SkillStack stack) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.start = start;
        this.deadline = deadline;
        this.stack = stack;
        this.status = Status.WAITING;
    }

    public String getName() {
        return name.toString();
    }

    public Priority getPriority() {
        return priority;
    }

    public String getDescription() {
        return description.toString();
    }

    public LocalDate getStart() {
        return start.getStart();
    }

    public LocalDate getDeadline() {
        return deadline.getDeadline();
    }

    public HashMap<Skill, Integer> getStack() {
        return stack.getStack();
    }

    public Status getStatus() {
        return status;
    }

    public void postponeProject(LocalDate newDate){
        try {
            this.deadline = new Deadline(newDate);
        } catch (InvalidAttributeException e) {
            e.printStackTrace();
        }
    }
}
