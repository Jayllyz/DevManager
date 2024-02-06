package domain.projects.attributes;

import shared.Skill;
import shared.exceptions.InvalidAttributeException;

import java.util.HashMap;

public class SkillStack {
    HashMap<Skill, Integer> stack;

    public SkillStack(HashMap<Skill, Integer> stack) throws InvalidAttributeException {
        if(stack == null) {
            throw new InvalidAttributeException("The skill stack of the project cannot be null");
        }

        for (Skill skill : stack.keySet()) {
            if (stack.get(skill) < 0) {
                throw new InvalidAttributeException("The skill stack required experience cannot have negative values");
            }
        }

        this.stack = stack;
    }

    public HashMap<Skill, Integer> getStack() {
        return stack;
    }
}
