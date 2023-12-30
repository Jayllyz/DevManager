package domain.developers;

import java.util.HashMap;

/**
 * The type Developer manager.
 */
// HEXAGON
public class DeveloperManager implements ManageDeveloper{

    public DeveloperManager(DeveloperRepository repository) {

        if(repository == null) {
            throw new IllegalArgumentException("repository can't be null");
        }

        this.repository = repository;
    }

    DeveloperRepository repository;

    @Override
    public Developer createDeveloper(String name, String email, HashMap<Skill, Integer> skills) {
        return null;
    }


}
