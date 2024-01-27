package shared.developers;

import org.junit.jupiter.api.Test;
import shared.exceptions.InvalidAttributeException;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            Email email = new Email(null);
        });

        String expectedMessage = "Developer email is not defined";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsMoreThan100Characters() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            Email email = new Email("johnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohn@gmail.com");
        });

        String expectedMessage = "Developer email can't be more than 100 characters";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            Email email = new Email("blatest@gmail.");
        });

        String expectedMessage = "Developer email format is invalid";
        assertEquals(expectedMessage,exception.getMessage());
    }

    // THIS TEST IS FOR OPQUAST VALIDITY !!!!!!!!!!!!!!!!
    @Test
    void shouldPassWithGmailPlusAttribute() {
        assertDoesNotThrow(() -> {
            Email email = new Email("bla+test@gmail.com");
        });
    }
}