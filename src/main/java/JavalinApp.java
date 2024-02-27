import domain.developers.Developer;
import domain.developers.DeveloperManager;
import infrastructure.developer.DTO.DeveloperMapper;
import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import infrastructure.shared.JavalinExceptionHandler;
import io.javalin.Javalin;
import shared.developers.Email;
import shared.exceptions.InvalidAttributeException;

public class JavalinApp {
    public void start() throws InvalidAttributeException {
        DeveloperManager developerManager = new DeveloperManager(new DeveloperFakeRepositoryAdapter());

        var app = Javalin.create(/*config*/);

        JavalinExceptionHandler.setApplicationExceptions(app);

        // Get developer by email
        app.get("/developer/{email}", ctx -> {
            String emailString = ctx.pathParam("email");
                Developer result = developerManager.getDeveloperByMail(new Email(emailString));
                ctx.json(DeveloperMapper.mapDeveloperToDTO(result));
        });

        app.start(8282);
    }
}