package domain.developers;

import shared.Experience;
import shared.Skill;
import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shared.developers.Email;
import shared.developers.Name;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.EntityAlreadyExistsException;
import shared.exceptions.InvalidAttributeException;
import shared.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class DeveloperTest {



    DeveloperRepository repository;
    private final ManageDeveloper devManager;

    DeveloperTest() {
        try {
            this.repository = new DeveloperFakeRepositoryAdapter();
        } catch (InvalidAttributeException e) {
            throw new RuntimeException(e);
        }

        this.devManager = new DeveloperManager(repository);
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
    @DisplayName("should throw EntityAlreadyExistsException when developer already exist")
    void shouldThrowExceptionWhenDeveloperAlreadyExists() {
        SkillsByYearsOfExperience skills = new SkillsByYearsOfExperience();
        skills.addNewSkill(Skill.CSS,Experience.SKILLED);
        Developer john;
        try {
            john = new Developer(new Name("john"),new Name("doe"), new Email("johndoe@gmail.com"), skills);
        } catch (InvalidAttributeException e) {
            throw new RuntimeException(e);
        }

        EntityAlreadyExistsException e = assertThrows(EntityAlreadyExistsException.class,() -> {
            Developer test = this.devManager.createDeveloper(john);
        });

        assertEquals("The email provided is already used by a developer",e.getMessage());

    }

    @Test
    @DisplayName("should get developer by mail when email is correct")
    void shouldGetDeveloperByMail() {

        Developer john = null;
        try {
            john = this.devManager.getDeveloperByMail(new Email("johndoe@gmail.com"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertNotNull(john);
        assertInstanceOf(Developer.class, john);
    }

    @Test
    @DisplayName("should throw NoEntityFoundException when developer is not found by mail")
    void shouldFailBadEmail() {
        EntityNotFoundException e = assertThrows(EntityNotFoundException.class,() -> {
            Developer john = this.devManager.getDeveloperByMail(new Email("test@gmail.com"));
        });

        String expectedMessage = "No developer was found with this email";
        assertEquals(expectedMessage,e.getMessage());
    }

    @Test
    @DisplayName("should remove a developer")
    void shouldRemoveDeveloper() {
        try {
            Email devEmail = new Email("marc@gmail.com");
            assertEquals(devEmail,this.devManager.removeDeveloper(devEmail));
            assertThrows(EntityNotFoundException.class,() -> {
                this.devManager.getDeveloperByMail(devEmail);
            });
        } catch (InvalidAttributeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("should get all developers")
    void shouldGetAllDevelopers() {
        List<Developer> developers = this.devManager.getAllDevelopers();
        assertNotNull(developers);
        assertEquals(3, developers.size());
    }

    @Test
    @DisplayName("should get 2 developers when asking for developers with skill SCRATCH")
    void shouldGet2DevelopersWhenAskingForSKilLScratch() {
        List<Developer> developers = this.devManager.getAllDevelopersBySkill(Skill.SCRATCH);

        assertEquals(2,developers.size());
    }

    @Test
    @DisplayName("should get 0 developers when asking for developers with skill JAVA")
    void shouldGetNoDevelopersWhenAskingForSKilLScratch() {
        List<Developer> developers = this.devManager.getAllDevelopersBySkill(Skill.JAVA);

        assertEquals(0,developers.size());
    }

    @Test
    @DisplayName("should get 1 developer when asking for experts in php")
    void shouldGetOneDeveloperWhenAskingForExpertsInPhp() {
        List<Developer> developers = this.devManager.getAllDevelopersBySkillAndExperience(Skill.PHP, Experience.EXPERT);
        assertEquals(1,developers.size());
    }


}