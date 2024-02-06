package domain.projects.attributes;

import org.junit.jupiter.api.Test;
import shared.Skill;
import shared.exceptions.InvalidAttributeException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SkillStackTest {
    @Test
    void shouldThrowExceptionWhenSkillStackIsNotSet() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            SkillStack stack = new SkillStack(null);
        });

        String expectedMessage = "The skill stack of the project cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSkillStackHasNegativeValues() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            SkillStack stack = new SkillStack(new HashMap<Skill, Integer>() {{
                put(Skill.C, -1);
            }});
        });

        String expectedMessage = "The skill stack required experience cannot have negative values";
        assertEquals(expectedMessage,exception.getMessage());
    }
}
