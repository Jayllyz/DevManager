package shared.teams;

import domain.developers.Developer;
import domain.teams.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.InvalidAttributeException;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NameTest {
    @Test
    @DisplayName("Should return an error when creating a team with a null name")
    void shouldReturnAnErrorOnNullName() {
        HashMap<Skill, Experience> skillSet1 = new HashMap<>();
        HashMap<Skill, Experience> skillSet2 = new HashMap<>();
        HashMap<Skill, Experience> skillSet3 = new HashMap<>();
        try {
            skillSet1.put(Skill.HTML, Experience.fromYearsOfExperience(4));
            skillSet2.put(Skill.SCRATCH, Experience.fromYearsOfExperience(4));
            skillSet3.put(Skill.COBOL, Experience.fromYearsOfExperience(4));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        Developer developer1;
        Developer developer2;
        Developer developer3;
        try {
            developer1 = new Developer(new shared.developers.Name("john"), new shared.developers.Name("Doe"), new Email("johndoe@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer2 = new Developer(new shared.developers.Name("Marc"), new shared.developers.Name("Robel"), new Email("marc@gmail.com"), new SkillsByYearsOfExperience(skillSet2));
            developer3 = new Developer(new shared.developers.Name("Jeanne"), new shared.developers.Name("Darc"), new Email("jeanne@gmail.com"), new SkillsByYearsOfExperience(skillSet3));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        List<Developer> listDeveloper = List.of(
                developer1,
                developer2,
                developer3
        );
        assertThrows(IllegalArgumentException.class, () -> new Team(new Name(null), new Developers(listDeveloper)));
    }

    @Test
    @DisplayName("Should return an error when team name is less than 3 characters")
    void shouldReturnAnErrorOnLessThan3Char() {
        HashMap<Skill, Experience> skillSet1 = new HashMap<>();
        HashMap<Skill, Experience> skillSet2 = new HashMap<>();
        HashMap<Skill, Experience> skillSet3 = new HashMap<>();
        try {
            skillSet1.put(Skill.HTML, Experience.fromYearsOfExperience(4));
            skillSet2.put(Skill.SCRATCH, Experience.fromYearsOfExperience(4));
            skillSet3.put(Skill.COBOL, Experience.fromYearsOfExperience(4));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        Developer developer1;
        Developer developer2;
        Developer developer3;
        try {
            developer1 = new Developer(new shared.developers.Name("john"), new shared.developers.Name("Doe"), new Email("johndoe@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer2 = new Developer(new shared.developers.Name("Marc"), new shared.developers.Name("Robel"), new Email("marc@gmail.com"), new SkillsByYearsOfExperience(skillSet2));
            developer3 = new Developer(new shared.developers.Name("Jeanne"), new shared.developers.Name("Darc"), new Email("jeanne@gmail.com"), new SkillsByYearsOfExperience(skillSet3));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        List<Developer> listDeveloper = List.of(
                developer1,
                developer2,
                developer3
        );
        assertThrows(IllegalArgumentException.class, () -> new Team(new Name("ab"), new Developers(listDeveloper)));
    }

    @Test
    @DisplayName("Should return an error when team name is more than 50 characters")
    void shouldReturnAnErrorOnLessThan50Char() {
        HashMap<Skill, Experience> skillSet1 = new HashMap<>();
        HashMap<Skill, Experience> skillSet2 = new HashMap<>();
        HashMap<Skill, Experience> skillSet3 = new HashMap<>();
        try {
            skillSet1.put(Skill.HTML, Experience.fromYearsOfExperience(4));
            skillSet2.put(Skill.SCRATCH, Experience.fromYearsOfExperience(4));
            skillSet3.put(Skill.COBOL, Experience.fromYearsOfExperience(4));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        Developer developer1;
        Developer developer2;
        Developer developer3;
        try {
            developer1 = new Developer(new shared.developers.Name("john"), new shared.developers.Name("Doe"), new Email("johndoe@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer2 = new Developer(new shared.developers.Name("Marc"), new shared.developers.Name("Robel"), new Email("marc@gmail.com"), new SkillsByYearsOfExperience(skillSet2));
            developer3 = new Developer(new shared.developers.Name("Jeanne"), new shared.developers.Name("Darc"), new Email("jeanne@gmail.com"), new SkillsByYearsOfExperience(skillSet3));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        List<Developer> listDeveloper = List.of(
                developer1,
                developer2,
                developer3
        );
        assertThrows(IllegalArgumentException.class, () -> new Team(new Name("something that is more than 50 characters for testings"), new Developers(listDeveloper)));
    }
}
