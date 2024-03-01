package infrastructure.shared.database;

import domain.developers.Developer;
import domain.developers.Project;
import domain.developers.Projects;
import infrastructure.developer.DTO.DeveloperDTO;
import infrastructure.developer.DTO.ProjectDTO;
import infrastructure.developer.DTO.ProjectMapper;
import shared.Experience;
import shared.Priority;
import shared.Skill;
import shared.Status;
import shared.developers.Email;
import shared.developers.Name;
import shared.developers.SkillsByYearsOfExperience;
import shared.projects.Deadline;
import shared.projects.SkillStack;
import shared.projects.StartDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class EntityMapper {

    public static Developer developerEntityToDomain(DeveloperEntity developerEntity) {

        String firstName = developerEntity.getFirstName();
        String lastName = developerEntity.getFirstName();
        String emailAddress = developerEntity.getEmail();
        SkillsByYearsOfExperience developerSkills = getSkillByExperience(developerEntity);
        List<ProjectEntity> projectEntities = developerEntity.getProjects();

        Projects projects = new Projects();

        for(ProjectEntity projectEntity : projectEntities) {
            Project project = projectEntityToDomain(projectEntity);
            projects.add(project);
        }

        return new Developer(
                new Name(firstName),
                new Name(lastName),
                new Email(emailAddress),
                developerSkills,
                projects
        );
    }

    private static SkillsByYearsOfExperience getSkillByExperience(DeveloperEntity developerEntity) {

        SkillsByYearsOfExperience skillExperience = new SkillsByYearsOfExperience();

        Set<DeveloperSkillsEntity> developerSkillsEntities = developerEntity.getSkillsEntities();

        for(DeveloperSkillsEntity devSkill : developerSkillsEntities) {
            String skillName = devSkill.getSkill().getName();
            Skill skill = Skill.valueOf(skillName.toUpperCase());
            Experience experience = Experience.fromYearsOfExperience(devSkill.getYearsExperience());

            skillExperience.addNewSkill(skill,experience);
        }

        return skillExperience;
    }

    public static Project projectEntityToDomain(ProjectEntity projectEntity) {

        String name = projectEntity.getName();
        LocalDate deadline = projectEntity.getDeadline().toLocalDate();
        LocalDate start = projectEntity.getStartDate().toLocalDate();
        Status projectStatus = Status.fromString(projectEntity.getStatus());
        Priority projectPriority = Priority.fromString(projectEntity.getPriority());
        SkillStack skillStack = getSkillsForProject(projectEntity);

        return new Project(
                new shared.projects.Name(name),
                projectPriority,
                new StartDate(start),
                new Deadline(deadline),
                skillStack,
                projectStatus
        );
    }

    private static SkillStack getSkillsForProject(ProjectEntity projectEntity) {
        SkillStack skillStack = new SkillStack();

        Set<SkillsForProjectEntity> projectSkills = projectEntity.getSkillsEntities();

        for(SkillsForProjectEntity projectSkill : projectSkills) {
            String skillName = projectSkill.getSkill().getName();
            Skill skill = Skill.valueOf(skillName.toUpperCase());
            Integer devNumbers = projectSkill.getDevNumber();

            skillStack.put(skill,devNumbers);
        }

        return skillStack;
    }
}
