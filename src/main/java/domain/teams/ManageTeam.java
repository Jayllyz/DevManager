package domain.teams;

import domain.developers.Developer;
import shared.exceptions.InvalidAttributeException;

import java.util.List;

public interface ManageTeam {
    Team createTeam(Project project, List<Developer> developers) throws InvalidAttributeException;
}
