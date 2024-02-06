package domain.developers;

import shared.Experience;
import shared.Skill;
import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shared.developers.Email;
import shared.developers.Name;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.InvalidAttributeException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class DeveloperTest {

    DeveloperRepository repository;

    {
        try {
            repository = new DeveloperFakeRepositoryAdapter();
        } catch (InvalidAttributeException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    @DisplayName("should create a developer")
    void shouldCreateADeveloper() throws InvalidAttributeException {

        HashMap<Skill, Experience> hashMap = new HashMap<>();
        hashMap.put(Skill.COBOL,Experience.fromYearsOfExperience(14));
        SkillsByYearsOfExperience skills = new SkillsByYearsOfExperience(hashMap);

        Developer john = null;
        try {
            john = new Developer(new Name("john"),new Name("doe"), new Email("johnnyjones@john.john"), skills);
        } catch (InvalidAttributeException e) {
            throw new RuntimeException(e);
        }
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