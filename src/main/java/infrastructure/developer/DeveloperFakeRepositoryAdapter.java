package infrastructure.developer;

import domain.developers.Developer;
import domain.developers.DeveloperRepository;
import domain.developers.Project;
import domain.developers.Projects;
import shared.Experience;
import shared.Priority;
import shared.Skill;
import shared.Status;
import shared.developers.Email;
import shared.developers.Name;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.InvalidAttributeException;
import shared.exceptions.EntityNotFoundException;
import shared.projects.Deadline;
import shared.projects.SkillStack;
import shared.projects.StartDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeveloperFakeRepositoryAdapter implements DeveloperRepository {

    HashMap<Skill,Experience> skillSet1 = new HashMap<>();
    HashMap<Skill,Experience> skillSet2 = new HashMap<>();
    HashMap<Skill,Experience> skillSet3 = new HashMap<>();

    List<Project> projects;

    List<Developer> developers = new ArrayList<>();

    public DeveloperFakeRepositoryAdapter() throws InvalidAttributeException {

        SkillStack skillStack1 = new SkillStack();
        SkillStack skillStack2 = new SkillStack();
        SkillStack skillStack3 = new SkillStack();

        skillStack1.put(Skill.C,4);

        skillStack2.put(Skill.SCRATCH,5);

        skillStack3.put(Skill.PHP, 2);
        skillStack3.put(Skill.COBOL, 4);
        skillStack3.put(Skill.COFFEE, 1);

        this.projects = new ArrayList<>(List.of(
                new Project(new shared.projects.Name("Calculator"), Priority.NORMAL, new StartDate(LocalDate.now().plusDays(1)), new Deadline(LocalDate.now().plusDays(20)), skillStack1, Status.CANCELLED),
                new Project(new shared.projects.Name("Spotify"), Priority.CRITICAL, new StartDate(LocalDate.now().plusDays(1)), new Deadline(LocalDate.now().plusDays(20)), skillStack2,Status.IN_PROGRESS),
                new Project(new shared.projects.Name("jsp"), Priority.NORMAL, new StartDate(LocalDate.now().plusDays(1)), new Deadline(LocalDate.now().plusDays(20)), skillStack3,Status.DONE)
        ));

        skillSet1.put(Skill.PHP, Experience.fromYearsOfExperience(2));
        skillSet1.put(Skill.COBOL,Experience.fromYearsOfExperience(1));
        skillSet1.put(Skill.COFFEE,Experience.fromYearsOfExperience(6));
        skillSet1.put(Skill.HTML,Experience.fromYearsOfExperience(14));

        skillSet2.put(Skill.PHP,Experience.fromYearsOfExperience(14));
        skillSet2.put(Skill.COBOL,Experience.fromYearsOfExperience(4));
        skillSet2.put(Skill.CSS,Experience.fromYearsOfExperience(1));
        skillSet2.put(Skill.SCRATCH,Experience.fromYearsOfExperience(23));

        skillSet3.put(Skill.PHP,Experience.fromYearsOfExperience(2));
        skillSet3.put(Skill.COBOL,Experience.fromYearsOfExperience(4));
        skillSet3.put(Skill.COFFEE,Experience.fromYearsOfExperience(1));
        skillSet3.put(Skill.SCRATCH,Experience.fromYearsOfExperience(2));

        Projects projects1 = new Projects();
        projects1.add(projects.get(2));

        Projects projectInProgress = new Projects();
        projectInProgress.add(projects.get(1));

        Projects projects3 = new Projects();
        projects3.add(projects.get(2));
        projects3.add(projects.get(0));

        this.developers.add(new Developer(new Name("john"),new Name("Doe"),new Email("johndoe@gmail.com"),new SkillsByYearsOfExperience(skillSet1), projects1));
        this.developers.add(new Developer(new Name("Marc"),new Name("Robel"),new Email("marc@gmail.com"),new SkillsByYearsOfExperience(skillSet2), projectInProgress));
        this.developers.add(new Developer(new Name("Jeanne"),new Name("Darc"),new Email("jeanne@gmail.com"),new SkillsByYearsOfExperience(skillSet3), projects3));
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
    public Developer getDeveloperByMail(Email email) throws EntityNotFoundException {
        for(Developer developer : this.developers) {
            String developerEmail = developer.getEmailAddress();
            if(developerEmail.equals(email.toString())) return developer;
        }

        throw new EntityNotFoundException("No developer was found with this email");
    }

    @Override
    public void removeDeveloper(Email email) {
        developers.removeIf(developer -> developer.getEmailAddress().equals(email.toString()));
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return developers;
    }

    @Override
    public List<Developer> getAllDevelopersBySkill(Skill skill) {
        List<Developer> developersBySkill = new ArrayList<>();

        for (Developer developer : developers) {
            if(developer.hasSkill(skill)) {
                developersBySkill.add(developer);
            }
        }

        return developersBySkill;
    }

    @Override
    public List<Developer> getAllDevelopersBySkillAndExperience(Skill skill, Experience experience) {
        List<Developer> developersBySkill = new ArrayList<>();

        for (Developer developer : developers) {
            if(developer.hasSkill(skill) && developer.getSkillExperience(skill) == experience) {
                developersBySkill.add(developer);
            }
        }

        return developersBySkill;
    }
}
