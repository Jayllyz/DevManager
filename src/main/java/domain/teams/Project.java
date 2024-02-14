package domain.teams;

import shared.Priority;
import shared.Skill;
import shared.projects.Deadline;
import shared.projects.SkillStack;
import shared.projects.StartDate;

import java.time.LocalDate;
import java.util.HashMap;

import static java.time.temporal.ChronoUnit.MONTHS;


public class Project {
    private Priority priority;
    private StartDate start;
    private Deadline deadline;

    private SkillStack skillStack;
    public Project(Priority priority, StartDate projectStart, Deadline deadline, SkillStack stack) {

        if(projectStart.toDate().isAfter(deadline.toDate())) {
            throw new IllegalArgumentException("Start date of the project cannot be after end date !");
        }

        this.priority = priority;
        this.start = projectStart;
        this.deadline = deadline;
        this.skillStack = stack;
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
        return skillStack.getStack();
    }

    public int getDurationInMonth() {
        return (int) MONTHS.between(getStart(),getDeadline());
    }


}
