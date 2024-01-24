package domain.teams.attributes;

import domain.developers.Developer;

import java.util.List;

public class Developers {
    private List<Developer> developers;

    public Developers(List<Developer> developers) {
        if(developers == null) {
            throw new IllegalArgumentException("Team developers are not defined");
        }
        if(developers.size() < 3){
            throw new IllegalArgumentException("Team must have at least 3 developers");
        }
        if(developers.size() > 8){
            throw new IllegalArgumentException("Team can't have more than 8 developers");
        }
        for(Developer developer : developers){
            int juniorCount = 0;

//            TODO: verifier que dans une equipe n'a pas plus de 3 juniors
//            TODO: en fonction des criteres pour savoir comment definir un junior
        }

        this.developers = developers;
    }
}
