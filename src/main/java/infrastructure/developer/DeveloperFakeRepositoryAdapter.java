package infrastructure.developer;

import domain.developers.Developer;
import domain.developers.DeveloperRepository;
import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.developers.Name;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.InvalidAttributeException;
import shared.exceptions.NoEntityFoundException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DeveloperFakeRepositoryAdapter implements DeveloperRepository {

    HashMap<Skill,Experience> skillSet1 = new HashMap<>();
    HashMap<Skill,Experience> skillSet2 = new HashMap<>();
    HashMap<Skill,Experience> skillSet3 = new HashMap<>();

    List<Developer> developers;

    {
        try {
            developers = List.of(
                    new Developer(new Name("john"),new Name("Doe"),new Email("johndoe@gmail.com"),new SkillsByYearsOfExperience(skillSet1)),
                    new Developer(new Name("Marc"),new Name("Robel"),new Email("marc@gmail.com"),new SkillsByYearsOfExperience(skillSet2)),
                    new Developer(new Name("Jeanne"),new Name("Darc"),new Email("jeanne@gmail.com"),new SkillsByYearsOfExperience(skillSet3))
            );
        } catch (InvalidAttributeException e) {
            throw new RuntimeException(e);
        }
    }

    public DeveloperFakeRepositoryAdapter() throws InvalidAttributeException {
        skillSet1.put(Skill.PHP, Experience.fromYearsOfExperience(2));
        skillSet1.put(Skill.COBOL,Experience.fromYearsOfExperience(1));
        skillSet1.put(Skill.COFFEE,Experience.fromYearsOfExperience(6));
        skillSet1.put(Skill.HTML,Experience.fromYearsOfExperience(14));

        skillSet2.put(Skill.PHP,Experience.fromYearsOfExperience(1));
        skillSet2.put(Skill.COBOL,Experience.fromYearsOfExperience(4));
        skillSet2.put(Skill.CSS,Experience.fromYearsOfExperience(1));
        skillSet2.put(Skill.SCRATCH,Experience.fromYearsOfExperience(23));

        skillSet3.put(Skill.PHP,Experience.fromYearsOfExperience(2));
        skillSet3.put(Skill.COBOL,Experience.fromYearsOfExperience(4));
        skillSet3.put(Skill.COFFEE,Experience.fromYearsOfExperience(1));
        skillSet3.put(Skill.HTML,Experience.fromYearsOfExperience(2));
    }

    @Override
    public Developer createDeveloper(Developer developer) {
        return null;
    }

    /**
     * Gets developer by mail.
     *
     * @param email the email
     * @return the developer by mail
     * @throws IllegalArgumentException the illegal argument exception
     */
    @Override
    public Developer getDeveloperByMail(String email) throws NoEntityFoundException {
        for(Developer developer : this.developers) {
            if(developer.getEmailAddress().equals(email)) return developer;
        }

        throw new NoEntityFoundException("No developer was found with this email");
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return developers;
    }
}
