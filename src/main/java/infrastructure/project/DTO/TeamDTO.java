package infrastructure.project.DTO;

import java.util.List;

public class TeamDTO {
    private List<DeveloperDTO> developers;

    public TeamDTO(List<DeveloperDTO> developers){
        this.developers = developers;
    }

    public List<DeveloperDTO> getDevelopers() {
        return developers;
    }
}
