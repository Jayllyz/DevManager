package domain.developers;

import shared.exceptions.NoEntityFoundException;

import java.util.List;

public interface DeveloperRepository {
    Developer createDeveloper(Developer developer);
    Developer getDeveloperByMail(String email) throws NoEntityFoundException;
    List<Developer> getAllDevelopers();
}
