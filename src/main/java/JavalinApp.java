import domain.developers.DeveloperManager;
import domain.developers.ManageDeveloper;
import infrastructure.developer.DeveloperControllerAdapter;
import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import infrastructure.shared.JavalinExceptionHandler;
import io.javalin.Javalin;
import shared.exceptions.InvalidAttributeException;

public class JavalinApp {
    public void start() throws InvalidAttributeException {
        ManageDeveloper developerManager = new DeveloperManager(new DeveloperFakeRepositoryAdapter());

        Javalin app = Javalin.create(/*config*/);

        JavalinExceptionHandler.setApplicationExceptions(app);

        // Developer Endpoints
        // GET
        app.get("developer/{email}", DeveloperControllerAdapter::getDeveloperByEmail);
        app.get("developer", DeveloperControllerAdapter::getAllDevelopers);

        // DELETE
        app.delete("developer/{email}", DeveloperControllerAdapter::deleteDeveloper);



        app.start(8282);
    }
}