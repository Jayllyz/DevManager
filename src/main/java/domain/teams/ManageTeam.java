package domain.teams;

import shared.exceptions.InvalidAttributeException;

import java.util.List;

public interface ManageTeam {
    Team createTeam(Project project, List<Developer> developers) throws InvalidAttributeException;
}
