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

public class DevelopersTest {
    @Test
    @DisplayName("Should return an error when creating a team with a null developer")
    void shouldReturnAnErrorOnNullDeveloper() {
        assertThrows(InvalidAttributeException.class, () -> new Team(new Name("Test"), new Developers(null)));
    }

    @Test
    @DisplayName("Should return an error when creating a team with less than 3 developers")
    void shouldReturnAnErrorOnLessThan3Developers() {
        HashMap<Skill, Experience> skillSet1 = new HashMap<>();
        HashMap<Skill, Experience> skillSet2 = new HashMap<>();
        try {
            skillSet1.put(Skill.HTML, Experience.fromYearsOfExperience(4));
            skillSet2.put(Skill.SCRATCH, Experience.fromYearsOfExperience(4));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        Developer developer1;
        Developer developer2;
        try {
            developer1 = new Developer(new shared.developers.Name("john"), new shared.developers.Name("Doe"), new Email("johndoe@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer2 = new Developer(new shared.developers.Name("Marc"), new shared.developers.Name("Robel"), new Email("marc@gmail.com"), new SkillsByYearsOfExperience(skillSet2));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        List<Developer> listDeveloper = List.of(
                developer1,
                developer2
        );
        assertThrows(InvalidAttributeException.class, () -> new Team(new Name("Test"), new Developers(listDeveloper)));
    }

    @Test
    @DisplayName("Should return an error when creating a team with more than 8 developers")
    void shouldReturnAnErrorOnMoreThan8Developers() {
        HashMap<Skill, Experience> skillSet1 = new HashMap<>();
        try {
            skillSet1.put(Skill.HTML, Experience.fromYearsOfExperience(4));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        Developer developer1;
        Developer developer2;
        Developer developer3;
        Developer developer4;
        Developer developer5;
        Developer developer6;
        Developer developer7;
        Developer developer8;
        Developer developer9;
        try {
            developer1 = new Developer(new shared.developers.Name("Arthas"), new shared.developers.Name("Menethil"), new Email("am@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer2 = new Developer(new shared.developers.Name("Garrosh"), new shared.developers.Name("Hurlenfer"), new Email("gh@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer3 = new Developer(new shared.developers.Name("Varian"), new shared.developers.Name("Wrynn"), new Email("vw@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer4 = new Developer(new shared.developers.Name("Grom"), new shared.developers.Name("Hurlenfer"), new Email("gh@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer5 = new Developer(new shared.developers.Name("Voljin"), new shared.developers.Name("Sombrelance"), new Email("vs@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer6 = new Developer(new shared.developers.Name("Ragnaros"), new shared.developers.Name("Feu"), new Email("rf@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer7 = new Developer(new shared.developers.Name("Neltharion"), new shared.developers.Name("Gardeterre"), new Email("ng@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer8 = new Developer(new shared.developers.Name("Varok"), new shared.developers.Name("Saurfang"), new Email("vs@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer9 = new Developer(new shared.developers.Name("Sylvanas"), new shared.developers.Name("Coursevent"), new Email("sc@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        List<Developer> listDeveloper = List.of(
                developer1,
                developer2,
                developer3,
                developer4,
                developer5,
                developer6,
                developer7,
                developer8,
                developer9
        );
        assertThrows(InvalidAttributeException.class, () -> new Team(new Name("Test"), new Developers(listDeveloper)));
    }

    @Test
    @DisplayName("Should return an error when creating a team with a junior and no expert")
    void shouldReturnAnErrorOnJuniorWithoutExpert() {
        HashMap<Skill, Experience> skillSet1 = new HashMap<>();
        HashMap<Skill, Experience> skillSet2 = new HashMap<>();
        try {
            skillSet1.put(Skill.HTML, Experience.fromYearsOfExperience(4));
            skillSet2.put(Skill.SCRATCH, Experience.fromYearsOfExperience(2));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        Developer developer1;
        Developer developer2;
        try {
            developer1 = new Developer(new shared.developers.Name("Malfurion"), new shared.developers.Name("Hurlorage"), new Email("ms@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer2 = new Developer(new shared.developers.Name("Jaina"), new shared.developers.Name("Portvaillant"), new Email("jp@gmail.com"), new SkillsByYearsOfExperience(skillSet2));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        List<Developer> listDeveloper = List.of(
                developer1,
                developer2
        );

        assertThrows(InvalidAttributeException.class, () -> new Team(new Name("Test"), new Developers(listDeveloper)));
    }

    @Test
    @DisplayName("Should return an error when creating a team with more than 3 junior developers")
    void shouldReturnAnErrorOnATeamWithMoreThan3Junior() {
        HashMap<Skill, Experience> skillSet1 = new HashMap<>();
        HashMap<Skill, Experience> skillSet2 = new HashMap<>();
        try {
            skillSet1.put(Skill.HTML, Experience.fromYearsOfExperience(9));
            skillSet2.put(Skill.SCRATCH, Experience.fromYearsOfExperience(2));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        Developer developer1;
        Developer developer2;
        Developer developer3;
        Developer developer4;
        Developer developer5;
        try {
            developer1 = new Developer(new shared.developers.Name("Malfurion"), new shared.developers.Name("Hurlorage"), new Email("ms@gmail.com"), new SkillsByYearsOfExperience(skillSet1));
            developer2 = new Developer(new shared.developers.Name("Jaina"), new shared.developers.Name("Portvaillant"), new Email("jp@gmail.com"), new SkillsByYearsOfExperience(skillSet2));
            developer3 = new Developer(new shared.developers.Name("Archimonde"), new shared.developers.Name("LeCorrupteur"), new Email("al@gmail.com"), new SkillsByYearsOfExperience(skillSet2));
            developer4 = new Developer(new shared.developers.Name("Kiljaeden"), new shared.developers.Name("LeTrompeur"), new Email("kl@gmail.com"), new SkillsByYearsOfExperience(skillSet2));
            developer5 = new Developer(new shared.developers.Name("Sargeras"), new shared.developers.Name("LeTitanNoir"), new Email("sl@gmail.com"), new SkillsByYearsOfExperience(skillSet2));
        } catch (InvalidAttributeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        List<Developer> listDeveloper = List.of(
                developer1,
                developer2,
                developer3,
                developer4,
                developer5
        );

        assertThrows(InvalidAttributeException.class, () -> new Team(new Name("Test"), new Developers(listDeveloper)));
    }
}
