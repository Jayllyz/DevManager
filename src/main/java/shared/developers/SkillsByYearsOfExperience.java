package shared.developers;
import shared.Experience;
import shared.Skill;
import shared.exceptions.InvalidAttributeException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillsByYearsOfExperience {
    HashMap<Skill, Experience> skillByExperience;

    public SkillsByYearsOfExperience() {
        this.skillByExperience = new HashMap<>();
    }

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

    public void addNewSkill(Skill skill, Experience experience) {
        this.skillByExperience.put(skill,experience);
    }

    public Experience getSkillExperience(Skill skill) {
        if(!hasSkill(skill)) return Experience.JUNIOR;
        return skillByExperience.get(skill);
    }

    public Experience getGlobalExperience() {
        Experience bestExperience = Experience.JUNIOR;
        for (Experience experience : this.skillByExperience.values()) {
            if(experience.getYearsOfExperience() > bestExperience.getYearsOfExperience()) bestExperience = experience;
        }

        return bestExperience;
    }

    public List<Skill> getSkills() {
        return List.copyOf(skillByExperience.keySet());
    }

    public boolean hasSkill(Skill skill) {
        return this.skillByExperience.containsKey(skill);
    }

}
