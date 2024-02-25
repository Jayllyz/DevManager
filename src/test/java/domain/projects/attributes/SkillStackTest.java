package domain.projects.attributes;

import org.junit.jupiter.api.Test;
import shared.Skill;
import shared.exceptions.InvalidAttributeException;
import shared.projects.SkillStack;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SkillStackTest {

    @Test
    void shouldThrowExceptionWhenSkillStackHasNegativeValues() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
                    SkillStack stack = new SkillStack();
                    stack.put(Skill.PHP, -1);
                });

        String expectedMessage = "The skill stack required developer cannot have negative values";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSkillStackHasMoreThan8Developers() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            SkillStack stack = new SkillStack();
            stack.put(Skill.C, 9);
        });

        String expectedMessage = "A skill in a skill stack cannot require more than 8 developers";
        assertEquals(expectedMessage,exception.getMessage());
    }
}
