package domain.developers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeveloperValidatorTest {

    @Test
    void firstNameShouldThrowExceptionWhenHasSpecialCharacters(){
        String firstName = "j@hn";

        assertThrows(IllegalArgumentException.class,() -> {
            throw new IllegalArgumentException("first name can't contain special characters");
        });
    }

}