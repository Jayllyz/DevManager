package shared.developers;
import shared.Experience;
import shared.Skill;
import shared.exceptions.InvalidAttributeException;

import java.util.HashMap;

public class SkillsByYearsOfExperience {
    HashMap<Skill, Experience> skillByExperience;

    public SkillsByYearsOfExperience(HashMap<Skill, Experience> stack) throws InvalidAttributeException {
        if(stack == null) {
            throw new InvalidAttributeException("The skill set of the developer cannot be null");
        }

        for (Skill skill : stack.keySet()) {
            Experience experience = stack.get(skill);
            if (experience.getYearsOfExperience() < 0) {
                throw new InvalidAttributeException("The skill experience of the developer cannot have negative values");
            }
        }

       this.skillByExperience = stack;
    }

    public int getSkillExperience(Skill skill) {
        if(!this.skillByExperience.containsKey(skill)) return -1;

        Experience experience = skillByExperience.get(skill);
        return experience.getYearsOfExperience();
    }

}
