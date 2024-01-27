package domain.developers;

import java.util.List;

/**
 * The type Developer manager.
 */
// HEXAGON
public class DeveloperManager implements ManageDeveloper{
    DeveloperRepository repository;

    public DeveloperManager(DeveloperRepository repository) {

        if(repository == null) {
            throw new IllegalArgumentException("repository can't be null");
        }

        this.repository = repository;
    }


    @Override
    public Developer createDeveloper(Developer developer) {
        this.repository.createDeveloper(developer);
        return developer;
    }

    @Override
    public Developer getDeveloperByMail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email can't be null");
        }

        return repository.getDeveloperByMail(email);
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return repository.getAllDevelopers();
    }


}
