package domain.projects.attributes;

import org.junit.jupiter.api.Test;
import shared.exceptions.InvalidAttributeException;
import shared.projects.Deadline;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    void shouldThrowExceptionWhenDeadlineIsNotSet() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            Deadline deadline = new Deadline(null);
        });

        String expectedMessage = "Deadline of project cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDeadlineIsInThePast() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            Deadline deadline = new Deadline(LocalDate.of(2020, 1, 1));
        });

        String expectedMessage = "Deadline of project cannot be in the past";
        assertEquals(expectedMessage,exception.getMessage());
    }
}
