package domain.developers;

import shared.developers.Email;
import shared.exceptions.EntityNotFoundException;

import java.util.List;

public interface DeveloperRepository {
    Developer createDeveloper(Developer developer);
    Developer getDeveloperByMail(Email email) throws EntityNotFoundException;
    void removeDeveloper(Email email);
    List<Developer> getAllDevelopers();
}
