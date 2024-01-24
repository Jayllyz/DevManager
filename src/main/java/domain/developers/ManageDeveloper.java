package domain.developers;

import domain.Skill;

import java.util.HashMap;
import java.util.List;

public interface ManageDeveloper {
    Developer createDeveloper(Developer developer);
    Developer getDeveloperByMail(String email);
    List<Developer> getAllDevelopers();
}
