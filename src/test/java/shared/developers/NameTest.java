package shared.developers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shared.exceptions.InvalidAttributeException;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {


    @Test
    void shouldThrowExceptionWhenNameContainSpecialCharacters() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            Name name = new Name("m@rtin");
        });

        String expectedMessage = "Developer name can't contain special characters";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsTooLong() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            Name name = new Name("laksdbfkajsbdflkajsdbflaksdbfasdfasdfasdfasdhfas");
        });

        String expectedMessage = "Developer name can't be more than 30 characters";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsTooShort() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            Name name = new Name("a");
        });

        String expectedMessage = "Developer name can't be less than 2 characters";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            Name name = new Name(null);
        });

        String expectedMessage = "Developer name is not defined";
        assertEquals(expectedMessage,exception.getMessage());
    }
}