package shared;

import org.junit.jupiter.api.Test;
import shared.exceptions.InvalidAttributeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StatusTest {
    @Test
    void shouldConvertIntToStatus() throws InvalidAttributeException {
        Status status = Status.fromString("waiting");
        assert(status == Status.WAITING);
    }

    @Test
    void shouldConvertIntToStatus2() throws InvalidAttributeException {
        Status status = Status.fromString("in progress");
        assert(status == Status.IN_PROGRESS);
    }

    @Test
    void shouldConvertIntToStatus3() throws InvalidAttributeException {
        Status status = Status.fromString("done");
        assert(status == Status.DONE);
    }

    @Test
    void shouldConvertIntToStatus4() throws InvalidAttributeException {
        Status status = Status.fromString("cancelled");
        assert(status == Status.CANCELLED);
    }

    @Test
    void shouldThrowExceptionWhenPriorityNotCorrect() {

        InvalidAttributeException exception = assertThrows(InvalidAttributeException.class, () -> {
            Status status = Status.fromString("asdf");
        });

        String expectedMessage = "Status must be waiting, in progress, done or cancelled";
        assertEquals(expectedMessage, exception.getMessage());

    }
}
