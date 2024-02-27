package domain.developers;

import shared.Priority;
import shared.Skill;
import shared.projects.Deadline;
import shared.projects.SkillStack;
import shared.projects.StartDate;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

public class Project {
    private StartDate start;
    private Deadline deadline;

    private SkillStack skillStack;
    public Project(Priority priority, StartDate projectStart, Deadline deadline, SkillStack stack) {

        if(projectStart.toDate().isAfter(deadline.toDate())) {
            throw new IllegalArgumentException("Start date of the project cannot be after end date !");
        }

        this.start = projectStart;
        this.deadline = deadline;
        this.skillStack = stack;
    }

    public LocalDate getStart() {
        return start.toDate();
    }

    public LocalDate getDeadline() {
        return deadline.toDate();
    }

    public int getDurationInMonth() {
        Period difference = Period.between(getStart(),getDeadline());
        int months = (int) difference.getYears() * 12 + difference.getMonths();
        if(difference.getDays() > 0) months++;

        return months;
    }


}