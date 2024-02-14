package shared;

import org.junit.jupiter.api.Test;
import shared.developers.Email;
import shared.exceptions.InvalidAttributeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriorityTest {
    @Test
    void shouldConvertStringToPriority() throws InvalidAttributeException {
        Priority priority = Priority.fromString("normal");
        assert(priority == Priority.NORMAL);
    }

    @Test
    void shouldConvertStringToPriority2() throws InvalidAttributeException {
        Priority priority = Priority.fromString("best-effort");
        assert(priority == Priority.BEST_EFFORT);
    }

    @Test
    void shouldConvertStringToPriority3() throws InvalidAttributeException {
        Priority priority = Priority.fromString("critical");
        assert(priority == Priority.CRITICAL);
    }

    @Test
    void shouldThrowExceptionWhenStringPriorityIsNotCorrect() {

        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            Priority priority = Priority.fromString("asdf");
        });

        String expectedMessage = "Priority must be normal, best-effort, or critical";
        assertEquals(expectedMessage,exception.getMessage());

    }
}
