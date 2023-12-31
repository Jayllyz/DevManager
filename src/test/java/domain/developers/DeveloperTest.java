package domain.developers;

import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class DeveloperTest {

    DeveloperRepository repository = new DeveloperFakeRepositoryAdapter();

    @Test
    @DisplayName("should create a developer")
    void shouldCreateADeveloper() {

        HashMap<Skill, Integer> skills = new HashMap<>();
        skills.put(Skill.COBOL,15);

        Developer john = new Developer("John", "johnnyjones@john.john", skills, LocalDate.of(2002,1,1));
        assertInstanceOf(Developer.class, john);
    }

    @Test
    @DisplayName("should get developer by mail")
    void shouldGetDeveloperByMail() {
        ManageDeveloper manager = new DeveloperManager(repository);
        Developer john = manager.getDeveloperByMail("johndoe@gmail.com");
        assertNotNull(john);
        assertInstanceOf(Developer.class, john);
    }

    @Test
    @DisplayName("should fail when email is null")
    void shouldFailWhenNullEmail() {
        ManageDeveloper manager = new DeveloperManager(repository);
        assertThrows(IllegalArgumentException.class, () -> manager.getDeveloperByMail(null));
    }

    @Test
    @DisplayName("should fail when email does not exist")
    void shouldFailBadEmail() {
        ManageDeveloper manager = new DeveloperManager(repository);
        Developer john = manager.getDeveloperByMail("test@gmail.com");
        assertNull(john);
    }

    @Test
    @DisplayName("should get all developers")
    void shouldGetAllDevelopers() {
        ManageDeveloper manager = new DeveloperManager(repository);
        List<Developer> developers = manager.getAllDevelopers();
        assertNotNull(developers);
        assertEquals(3, developers.size());
    }
}