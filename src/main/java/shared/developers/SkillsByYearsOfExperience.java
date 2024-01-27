package shared.developers;
import shared.Skill;
import shared.exceptions.InvalidAttributeException;

import java.util.HashMap;

public class SkillsByYearsOfExperience {
    HashMap<Skill, Integer> skillByYearsOfExperience;

    public SkillsByYearsOfExperience(HashMap<Skill, Integer> stack) throws InvalidAttributeException {
        if(stack == null) {
            throw new InvalidAttributeException("The skill set of the developer cannot be null");
        }

        for (Skill skill : stack.keySet()) {
            if (stack.get(skill) < 0) {
                throw new InvalidAttributeException("The skill experience of the developer cannot have negative values");
            }
        }

       this.skillByYearsOfExperience = stack;
    }

    public int getSkillExperience(Skill skill) {
        if(!this.skillByYearsOfExperience.containsKey(skill)) return -1;

        return skillByYearsOfExperience.get(skill);
    }

}
