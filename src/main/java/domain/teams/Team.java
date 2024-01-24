package domain.teams;

import domain.developers.Developer;

import java.util.List;

public class Team {
    private String name;
    private List<Developer> developers;

    public Team(String name, List<Developer> developers) {
        this.name = name;
        this.developers = developers;
    }
}
