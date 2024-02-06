package shared.projects;

import org.junit.jupiter.api.Test;
import shared.projects.Name;
import shared.exceptions.InvalidAttributeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NameTest {
    @Test
    void shouldThrowExceptionWhenNameIsNotSet() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            Name name = new Name(null);
        });

        String expectedMessage = "Project name is not defined";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBelowThreeChar() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            Name name = new Name("a");
        });

        String expectedMessage = "Project name can't be less than 3 characters";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsHigherThanFiftyChar() {
        InvalidAttributeException exception =  assertThrows(InvalidAttributeException.class,() -> {
            Name name = new Name("laksdbfkajsbdflkajsdbflaksdbfasdfasdfasdfasdhfaslaksdbfkajsbdflkajsdbflaksdbfasdfasdfasdfasdhfas");
        });

        String expectedMessage = "Project name can't be more than 50 characters";
        assertEquals(expectedMessage,exception.getMessage());
    }
}
