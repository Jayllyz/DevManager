import domain.developers.Developer;
import domain.developers.DeveloperManager;
import domain.developers.Project;
import domain.developers.Projects;
import infrastructure.developer.DTO.DeveloperMapper;
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

    public void start() throws InvalidAttributeException {
        DeveloperManager developerManager = new DeveloperManager(new DeveloperFakeRepositoryAdapter());

        var app = Javalin.create(/*config*/).start(8282);

        // Get developer by email
        app.get("/developer/{email}", ctx -> {
            String emailString = ctx.pathParam("email");
            try {
                Developer result = developerManager.getDeveloperByMail(new Email(emailString));
                ctx.json(DeveloperMapper.mapDeveloperToDTO(result));
            } catch (Exception e) {
                ExceptionHandler.handleException(e, ctx);
            }
        });
    }
}