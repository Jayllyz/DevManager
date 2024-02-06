package domain.projects;

import shared.Skill;
import domain.projects.attributes.*;

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

    public Project(String name, Priority priority, String description, LocalDate start, LocalDate deadline, HashMap<Skill, Integer> stack) {
        this.name = new Name(name);
        this.priority = priority;
        this.description = new Description(description);
        this.start = new Start(start);
        this.deadline = new Deadline(deadline);
        this.stack = new SkillStack(stack);
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
        this.start = new Start(newDate);
    }
}
