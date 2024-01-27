package shared.developers;

import org.junit.jupiter.api.Test;
import shared.Skill;
import shared.exceptions.InvalidAttributeException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SkillsByYearsOfExperienceTest {

    @Test
    void shouldThrowExceptionWhenSkillIsNull() {

        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            SkillsByYearsOfExperience skills = new SkillsByYearsOfExperience(null);
        });

        String expectedMessage = "The skill set of the developer cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenExperienceIsNegative() {
        HashMap<Skill, Integer> skillByYearsOfExperience = new HashMap<>();
        skillByYearsOfExperience.put(Skill.PHP, -1);

        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            SkillsByYearsOfExperience skills = new SkillsByYearsOfExperience(skillByYearsOfExperience);
        });

        String expectedMessage = "The skill experience of the developer cannot have negative values";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void shouldReturn5YearsForPHP() {
        HashMap<Skill, Integer> skillByYearsOfExperience = new HashMap<>();
        skillByYearsOfExperience.put(Skill.PHP, 5);

        try {
            SkillsByYearsOfExperience skills = new SkillsByYearsOfExperience(skillByYearsOfExperience);
            assertEquals(5,skills.getSkillExperience(Skill.PHP));
        } catch (InvalidAttributeException ignored) {}


    }

    @Test
    void shouldReturnNegativeOneWhenExperienceDoesntExist() {
        HashMap<Skill, Integer> skillByYearsOfExperience = new HashMap<>();
        skillByYearsOfExperience.put(Skill.PHP, 5);

        try {
            SkillsByYearsOfExperience skills = new SkillsByYearsOfExperience(skillByYearsOfExperience);
            assertEquals(-1,skills.getSkillExperience(Skill.HTML));
        } catch (InvalidAttributeException ignored) {}


    }
}