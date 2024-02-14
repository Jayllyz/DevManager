package shared.teams;

import domain.developers.Developer;
import shared.Experience;
import shared.Skill;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.InvalidAttributeException;

import java.util.HashMap;
import java.util.List;

public class Developers {
    private List<Developer> developers;

    public Developers(List<Developer> developers) throws InvalidAttributeException {
        if(developers == null) {
            throw new InvalidAttributeException("Team developers are not defined");
        }
        if(developers.size() < 3){
            throw new InvalidAttributeException("Team must have at least 3 developers");
        }
        if(developers.size() > 8){
            throw new InvalidAttributeException("Team can't have more than 8 developers");
        }

        int junior = 0;
        int expert = 0;

        for (Developer developer : developers) {
            if(developer == null){
                throw new InvalidAttributeException("Developer is not defined");
            }

            int highest = 0;
            List<Skill> skills = developer.getSkills();
            for (Skill skill : skills) {
                Experience experience = developer.getSkillExperience(skill);
                if(experience.getYearsOfExperience() > highest){
                    highest = experience.getYearsOfExperience();
                }
            }

            if(highest <= 3){
                junior++;
            }
            if(highest >= 5){
                expert++;
            }
        }

        if(junior > 0 && expert == 0){
            throw new InvalidAttributeException("Team can't have a junior developer without an expert developer");
        }

        if(junior > 3){
            throw new InvalidAttributeException("Team can't have more than 3 junior developers");
        }

        this.developers = developers;
    }
}
