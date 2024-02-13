package domain.teams;

import domain.developers.Developer;
import shared.Skill;
import shared.teams.Developers;
import shared.teams.Name;

import java.util.HashMap;

public class Team {
    private Name name;
    private Developers developers;

    public Team(Name name, Developers developers) {
        this.name = name;
        this.developers = developers;
    }
}
