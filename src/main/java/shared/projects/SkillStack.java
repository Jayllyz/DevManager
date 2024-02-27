package shared.projects;

import shared.Skill;
import shared.exceptions.InvalidAttributeException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class SkillStack {
    HashMap<Skill, Integer> stack;
    private int currentDevelopersCount;

    public SkillStack() {
        this.stack = new HashMap<>();
        this.currentDevelopersCount = 0;
    }

    public void put(Skill skill,Integer devNumberRequired) throws InvalidAttributeException {

        if (devNumberRequired < 0) {
            throw new InvalidAttributeException("The skill stack required developer cannot have negative values");
        }

        if(devNumberRequired > 8) {
            throw new InvalidAttributeException("A skill in a skill stack cannot require more than 8 developers");
        }

        if(currentDevelopersCount == 8) {
            throw new InvalidAttributeException("You cannot required more than 8 developers in a team. Current number is : " + this.currentDevelopersCount);
        }

        this.stack.put(skill, devNumberRequired);
        currentDevelopersCount++;

    }

    public int get(Skill skill) {
        if(!this.stack.containsKey(skill)) {
            throw new IllegalArgumentException("skill does not exist in the skill stack");
        }

        return stack.get(skill);
    }

    public List<Skill> getSkills() {
        return new ArrayList<>(stack.keySet());
    }

    public HashMap<Skill, Integer> getStack() {
        return stack;
    }
}
