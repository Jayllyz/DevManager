package infrastructure.developer;

import domain.developers.Developer;
import domain.developers.DeveloperRepository;
import domain.Skill;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class DeveloperFakeRepositoryAdapter implements DeveloperRepository {

    HashMap<Skill,Integer> skillSet1 = new HashMap<>();
    HashMap<Skill,Integer> skillSet2 = new HashMap<>();
    HashMap<Skill,Integer> skillSet3 = new HashMap<>();

    List<Developer> developers = List.of(
            new Developer("John","johndoe@gmail.com",skillSet1, LocalDate.of(2021,1,12)),
            new Developer("Marc","marc@gmail.com",skillSet2, LocalDate.of(2020,3,22)),
            new Developer("Jeanne","jeanne@gmail.com",skillSet3, LocalDate.of(2023,5,24))
    );

    public DeveloperFakeRepositoryAdapter() {
        skillSet1.put(Skill.PHP,3);
        skillSet1.put(Skill.COBOL,1);
        skillSet1.put(Skill.COFFEE,6);
        skillSet1.put(Skill.HTML,14);

        skillSet2.put(Skill.PHP,1);
        skillSet2.put(Skill.COBOL,4);
        skillSet2.put(Skill.CSS,1);
        skillSet2.put(Skill.SCRATCH,23);

        skillSet3.put(Skill.PHP,2);
        skillSet3.put(Skill.COBOL,4);
        skillSet3.put(Skill.COFFEE,1);
        skillSet3.put(Skill.HTML,2);
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
    public Developer getDeveloperByMail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email can't be null");
        }

        return developers.stream().filter(developer -> developer.getEmailAddress().equals(email)).findFirst().orElse(null);
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return developers;
    }
}
