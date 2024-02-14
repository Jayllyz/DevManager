package domain.projects.attributes;

import org.junit.jupiter.api.Test;
import shared.exceptions.InvalidAttributeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StartTest {
    @Test
    void shouldThrowExceptionWhenStartIsNotSet() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            Start start = new Start(null);
        });

        String expectedMessage = "Start date cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }
}
