package infrastructure.developer.DTO;

import domain.developers.Developer;
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
        for (int i = 0; i < developer.getProjects().listProjects().size(); i++) {
            projects.add(ProjectMapper.mapProjectToDTO(developer.getProjects().listProjects().get(i)));
        }

        return new DeveloperDTO(firstName, lastName, emailAddress, skillByExperience, projects);
    }
}
