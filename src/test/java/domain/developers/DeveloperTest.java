package domain.developers;

import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.*;

class DeveloperTest {

    DeveloperRepository repository = new DeveloperFakeRepositoryAdapter();

    @Test
    void shouldCreateADeveloper() {

        HashMap<Skill, Integer> skills = new HashMap<>();
        skills.put(Skill.COBOL,15);

        Developer john = new Developer("John", "johnnyjones@john.john", skills, LocalDate.of(2002,1,1));
        assertInstanceOf(Developer.class, john);
    }

    @Test
    void shouldGetDeveloperMail() {
        Developer john = repository.getDeveloperByMail("johndoe@gmail.com");
        assertNotNull(john);
        assertEquals("John", john.getName());
        assertInstanceOf(Developer.class, john);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenEmailIsNull() {
        assertThrows(IllegalArgumentException.class, () -> repository.getDeveloperByMail(null));
    }

    @Test
    void shouldFailWhenEmailIsNotInDatabase() {
        Developer john = repository.getDeveloperByMail("test@gmail.com");
        assertNull(john);
    }
}