package domain.developers;

import shared.Skill;

import java.util.List;

public interface ManageDeveloper {
    Developer createDeveloper(Developer developer);
    Developer getDeveloperByMail(String email);
    List<Developer> getAllDevelopers();

    Developer getAllDevelopersBySkill(Skill skill);

//    Developer getAllDevelopersByExperience(Skill skill, Experience experience)
}
