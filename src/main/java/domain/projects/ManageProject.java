package domain.projects;

import domain.Skill;

import java.time.LocalDate;
import java.util.HashMap;

public interface ManageProject {
    Project createProject(String name, int priority, String description, LocalDate start, LocalDate deadline, HashMap<Skill, Integer> stack);
}
