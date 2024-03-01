package infrastructure.shared.database;

import infrastructure.developer.DTO.DeveloperDTO;
import infrastructure.developer.DTO.ProjectDTO;
import jakarta.persistence.Entity;
import shared.Experience;
import shared.Priority;
import shared.Skill;
import shared.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class EntityMapper {

    public static DeveloperDTO developerEntityToDTO(DeveloperEntity developerEntity) {
        DeveloperDTO developerDTO = new DeveloperDTO();

        developerDTO.setEmailAddress(developerEntity.getEmail());
        developerDTO.setFirstName(developerEntity.getFirstName());
        developerDTO.setLastName(developerEntity.getLastName());

        HashMap<Skill,Experience> developerSkills = getSkillByExperience(developerEntity);
        developerDTO.setSkillByExperience(developerSkills);

        List<ProjectDTO> projects = new ArrayList<>();

        for(ProjectEntity projectEntity : developerEntity.getProjects()) {
            ProjectDTO projectDTO = projectEntityToDTO(projectEntity);
            projects.add(projectDTO);
        }

        developerDTO.setProjects(projects);

        return developerDTO;
    }

    private static HashMap<Skill, Experience> getSkillByExperience(DeveloperEntity developerEntity) {
        HashMap<Skill,Experience> skillExperienceHashMap = new HashMap<>();

        Set<DeveloperSkillsEntity> developerSkillsEntities = developerEntity.getSkillsEntities();

        for(DeveloperSkillsEntity devSkill : developerSkillsEntities) {
            String skillName = devSkill.getSkill().getName();
            Skill skill = Skill.valueOf(skillName.toUpperCase());
            Experience experience = Experience.fromYearsOfExperience(devSkill.getYearsExperience());

            skillExperienceHashMap.put(skill,experience);
        }

        return skillExperienceHashMap;
    }

    public static ProjectDTO projectEntityToDTO(ProjectEntity projectEntity) {
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setName(projectEntity.getName());
        projectDTO.setDeadline(projectEntity.getDeadline().toLocalDate());
        projectDTO.setStart(projectEntity.getStartDate().toLocalDate());
        Status projectStatus = Status.fromString(projectEntity.getStatus());
        Priority projectPriority = Priority.fromString(projectEntity.getPriority());

        HashMap<Skill,Integer> skillRequired = getSkillsForProject(projectEntity);
        projectDTO.setSkillStack(skillRequired);

        return projectDTO;
    }

    private static HashMap<Skill, Integer> getSkillsForProject(ProjectEntity projectEntity) {
        HashMap<Skill, Integer> skillStack = new HashMap<>();

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
