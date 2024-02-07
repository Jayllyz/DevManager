package shared.teams;

import domain.developers.Developer;
import shared.Skill;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.InvalidAttributeException;

import java.util.HashMap;

public class Developers {
    private HashMap<Skill, Developer> developers;

    public Developers(HashMap<Skill, Developer> developers) throws InvalidAttributeException {
        if(developers == null) {
            throw new InvalidAttributeException("Team developers are not defined");
        }
        if(developers.size() < 3){
            throw new InvalidAttributeException("Team must have at least 3 developers");
        }
        if(developers.size() > 8){
            throw new InvalidAttributeException("Team can't have more than 8 developers");
        }

        for (Developer developer : developers.values()) {

        }

        this.developers = developers;
    }
}
