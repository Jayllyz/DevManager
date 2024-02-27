package domain.projects.attributes;

import org.junit.jupiter.api.Test;
import shared.exceptions.InvalidAttributeException;
import shared.projects.StartDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StartTest {
    @Test
    void shouldThrowExceptionWhenStartIsNotSet() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            StartDate start = new StartDate(null);
        });

        String expectedMessage = "Start date of project cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }
}
