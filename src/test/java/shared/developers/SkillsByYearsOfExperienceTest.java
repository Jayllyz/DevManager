package shared.developers;

import org.junit.jupiter.api.Test;
import shared.Experience;
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
        HashMap<Skill, Experience> skillByYearsOfExperience = new HashMap<>();

        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            skillByYearsOfExperience.put(Skill.PHP, Experience.fromYearsOfExperience(-1));
        });

        String expectedMessage = "years of experience can't have a negative value";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void shouldReturnExpertExperienceWhenSKillIsPhp() throws InvalidAttributeException {
        HashMap<Skill, Experience> skillByYearsOfExperience = new HashMap<>();
        skillByYearsOfExperience.put(Skill.PHP, Experience.fromYearsOfExperience(5));

        try {
            SkillsByYearsOfExperience skills = new SkillsByYearsOfExperience(skillByYearsOfExperience);
            assertEquals(Experience.SKILLED,skills.getSkillExperience(Skill.PHP));
        } catch (InvalidAttributeException ignored) {}


    }

    @Test
    void shouldReturnNegativeOneWhenExperienceDoesntExist() throws InvalidAttributeException {
        HashMap<Skill, Experience> skillByYearsOfExperience = new HashMap<>();
        skillByYearsOfExperience.put(Skill.PHP, Experience.fromYearsOfExperience(5));

        try {
            SkillsByYearsOfExperience skills = new SkillsByYearsOfExperience(skillByYearsOfExperience);
            assertEquals(Experience.JUNIOR,skills.getSkillExperience(Skill.HTML));
        } catch (InvalidAttributeException ignored) {}


    }
}