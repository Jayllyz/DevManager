package domain.teams;

import shared.Priority;
import shared.Skill;
import shared.Status;
import shared.exceptions.InvalidAttributeException;
import shared.projects.Deadline;
import shared.projects.Name;
import shared.projects.SkillStack;
import shared.projects.StartDate;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

public class Project {
    private Name name;
    private Priority priority;
    private StartDate start;
    private Deadline deadline;
    private SkillStack stack;
    private Status status;

    public Project(Name name, Priority priority, StartDate projectStart, Deadline deadline, SkillStack stack, Status status) {

        if(projectStart.toDate().isAfter(deadline.toDate())) {
            throw new IllegalArgumentException("Start date of the project cannot be after end date !");
        }

        this.name = name;
        this.priority = priority;
        this.start = projectStart;
        this.deadline = deadline;
        this.stack = stack;
        this.status = Status.WAITING;
    }

    public int getDurationInMonth() {
        LocalDate start = getStart();
        LocalDate deadline = getDeadline();


        Period period = Period.between(start, deadline);
        int monthDuration = period.getYears() * 12 + period.getMonths();
        if(period.getDays() > 0 ) {
            monthDuration++;
        }

        return monthDuration;
    }
    public String getName() {
        return name.toString();
    }

    public Priority getPriority() {
        return priority;
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
