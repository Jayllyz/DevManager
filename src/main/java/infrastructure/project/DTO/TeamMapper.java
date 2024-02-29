package infrastructure.project.DTO;

import domain.projects.Developer;
import domain.projects.Team;

import java.util.List;

public class TeamMapper {
    public static TeamDTO mapTeamToDTO(Team team) {
        List<DeveloperDTO> developersDTOS;

        List<Developer> developers = team.getDevelopers();

        for(Developer developer : developers) {
            DeveloperDTO developerDTO = DeveloperMapper.mapDeveloperToDTO(developer);
        }

    }
}
