package domain.developers;

import java.util.List;

public interface DeveloperRepository {
    Developer createDeveloper(Developer developer);
    Developer getDeveloperByMail(String email);
    List<Developer> getAllDevelopers();
}
