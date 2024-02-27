import domain.developers.Developer;
import domain.developers.DeveloperManager;
import domain.developers.Project;
import domain.developers.Projects;
import infrastructure.developer.DTO.ProjectMapper;
import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import io.javalin.Javalin;
import shared.Experience;
import shared.Priority;
import shared.Skill;
import shared.Status;
import shared.developers.Email;
import shared.developers.Name;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.InvalidAttributeException;
import shared.projects.Deadline;
import shared.projects.SkillStack;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import shared.projects.StartDate;

import java.time.LocalDate;

public class JavalinApp {

    public static void main(String[] args) throws InvalidAttributeException {

        DeveloperManager developerManager = new DeveloperManager(new DeveloperFakeRepositoryAdapter());


        var app = Javalin.create(/*config*/);
        app.get("/developer/{email}", ctx -> {
            String emailString = ctx.pathParam("email");
            try {

                SkillsByYearsOfExperience skills = new SkillsByYearsOfExperience();
                skills.addNewSkill(Skill.PHP, Experience.SKILLED);

                SkillStack skillStack = new SkillStack();
                skillStack.put(Skill.PHP,1);

                Projects projects = new Projects();
                projects.add(new Project(
                        new shared.projects.Name("test"),
                        Priority.NORMAL,
                        new StartDate(LocalDate.now().plusDays(1)),
                        new Deadline(LocalDate.now().plusDays(150)),
                        skillStack,
                        Status.DONE
                ));

                Developer test = new Developer(new Name("blabla"),new Name("toto"),new Email(emailString),skills,
                        projects);

                SkillStack skillStack1 = new SkillStack();
                skillStack1.put(Skill.C,4);
                Project testt = new Project(new shared.projects.Name("Calculator"), Priority.NORMAL, new StartDate(LocalDate.now().plusDays(1)), new Deadline(LocalDate.now().plusDays(20)), skillStack1, Status.WAITING);


                ctx.json(ProjectMapper.mapProjectToDTO(testt));
            } catch (Exception e) {
                ctx.result("error = " + e.getMessage());
            }
        }).start(8282);
    }
}