package infrastructure.developer.DTO;

import domain.developers.Developer;
import domain.developers.Project;
import shared.Experience;
import shared.Skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeveloperMapper {
    public static DeveloperDTO mapDeveloperToDTO(Developer developer) {
        String firstName = developer.getFirstName();
        String lastName = developer.getLastName();
        String emailAddress = developer.getEmailAddress();
        HashMap<Skill, Experience> skillByExperience = developer.getSkillsByYearsOfExperience();
        List<ProjectDTO> projects = new ArrayList<>();

        List<Project> projectList = developer.getProjects().listProjects();
        for (int i = 0; i < projectList.size(); i++) {
            projects.add(ProjectMapper.mapProjectToDTO(projectList.get(i)));
        }

        return new DeveloperDTO(firstName, lastName, emailAddress, skillByExperience, projects);
    }
}
