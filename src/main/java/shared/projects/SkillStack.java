package shared.projects;

import shared.Skill;
import shared.exceptions.InvalidAttributeException;

import java.util.HashMap;

public class SkillStack {
    HashMap<Skill, Integer> stack;

    public SkillStack() {
        this.stack = new HashMap<>()    ;
    }

    public SkillStack(HashMap<Skill, Integer> stack) throws InvalidAttributeException {
        if(stack == null) {
            throw new InvalidAttributeException("The skill stack of the project cannot be null");
        }

        for (Skill skill : stack.keySet()) {
            if (stack.get(skill) < 0) {
                throw new InvalidAttributeException("The skill stack required developer cannot have negative values");
            }
        }

        int requiredDevelopers = 0;
        for (Skill skill : stack.keySet()) {
            requiredDevelopers += stack.get(skill);
        }

        if(requiredDevelopers > 8) {
            throw new InvalidAttributeException("A skill in a skill stack cannot require more than 8 developers");
        }

        this.stack = stack;
    }

    public void put(Skill skill,Integer devNumberRequired) throws InvalidAttributeException {
        if(devNumberRequired > 8) {
            throw new InvalidAttributeException("A skill in a skill stack cannot require more than 8 developers");
        }

        this.stack.put(skill, devNumberRequired);
    }

    public int get(Skill skill) {
        if(!this.stack.containsKey(skill)) {
            throw new IllegalArgumentException("skill does not exist in the skill stack");
        }

        return stack.get(skill);
    }

    public HashMap<Skill, Integer> getStack() {
        return stack;
    }
}
