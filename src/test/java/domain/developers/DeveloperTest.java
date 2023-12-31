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
        ManageDeveloper manager = new DeveloperManager(repository);
        Developer john = manager.getDeveloperByMail("johndoe@gmail.com");
        assertNotNull(john);
        assertInstanceOf(Developer.class, john);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenEmailIsNull() {
        ManageDeveloper manager = new DeveloperManager(repository);
        assertThrows(IllegalArgumentException.class, () -> manager.getDeveloperByMail(null));
    }

    @Test
    void shouldFailWhenEmailIsNotInDatabase() {
        ManageDeveloper manager = new DeveloperManager(repository);
        Developer john = manager.getDeveloperByMail("test@gmail.com");
        assertNull(john);
    }
}