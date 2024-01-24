package domain.teams;

import domain.developers.Developer;

import java.util.List;

public interface ManageTeam {
    Team createTeam(String name, List<Developer> developers);
}
