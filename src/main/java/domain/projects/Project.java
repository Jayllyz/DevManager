package domain.projects;

import domain.Skill;

import java.time.LocalDate;
import java.util.HashMap;

public class Project {
    private String name;
    private int priority;
    private String description;
    private LocalDate start;
    private LocalDate deadline;
    private HashMap<Skill, Integer> stack;

    public Project(String name, int priority, String description, LocalDate start, LocalDate deadline, HashMap<Skill, Integer> stack) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.start = start;
        this.deadline = deadline;
        this.stack = stack;
    }
}
