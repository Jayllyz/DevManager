package domain.developers;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DeveloperTest {


    @Test
    void shouldCreateADeveloper() {

        HashMap<Skill, Integer> skills = new HashMap<>();
        skills.put(Skill.COBOL,15);

        Developer john = new Developer("John", "johnnyjones@john.john", skills, LocalDate.of(2002,1,1));
        assertInstanceOf(Developer.class, john);
    }
}