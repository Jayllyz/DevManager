package infrastructure.project.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import shared.Skill;
import shared.Status;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class ProjectDTO {
    private String name;
    private String priority;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate start;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
    private HashMap<Skill, Integer> skillStack;
    private Status status;
    private List<DeveloperDTO> team;

    @JsonCreator
    public ProjectDTO(
            @JsonProperty("name") String name,
            @JsonProperty("priority") String priority,
            @JsonProperty("description") String description,
            @JsonProperty("startDate") LocalDate start,
            @JsonProperty("deadline") LocalDate deadline,
            @JsonProperty("stack") HashMap<Skill, Integer> stack,
            @JsonProperty("status") Status status,
            @JsonProperty("team") List<DeveloperDTO> team) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.start = start;
        this.deadline = deadline;
        this.skillStack = stack;
        this.status = status;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public String getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
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

    public List<DeveloperDTO> getTeam() {
        return team;
    }
}
