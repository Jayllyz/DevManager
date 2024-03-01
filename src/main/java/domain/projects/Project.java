package domain.projects;

import shared.Priority;
import shared.Skill;
import domain.projects.attributes.*;
import shared.Status;
import shared.projects.*;
import shared.exceptions.InvalidAttributeException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;

public class Project {
    private Name name;
    private Priority priority;
    private Description description;
    private StartDate start;
    private Deadline deadline;
    private SkillStack stack;
    private Status status;

    public Project(Name name, Priority priority, Description description, StartDate projectStart, Deadline deadline, SkillStack stack,Status status) {

        if(projectStart.toDate().isAfter(deadline.toDate())) {
            throw new IllegalArgumentException("Start date of the project cannot be after end date !");
        }

        this.name = name;
        this.priority = priority;
        this.description = description;
        this.start = projectStart;
        this.deadline = deadline;
        this.stack = stack;
        this.status = Status.WAITING;
    }

    public int getDurationInMonth() {
        LocalDate start = getStart();
        LocalDate deadline = getDeadline();


        Period period = Period.between(start, deadline);

        return period.getYears() * 12 + period.getMonths();
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
        return start.toDate();
    }

    public LocalDate getDeadline() {
        return deadline.toDate();
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
