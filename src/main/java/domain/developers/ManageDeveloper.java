package domain.developers;

import java.util.HashMap;
import java.util.List;

public interface ManageDeveloper {
    Developer createDeveloper(String name, String email, HashMap<Skill, Integer> skills);
    Developer getDeveloperByMail(String email);

    List<Developer> getAllDevelopers();
}
