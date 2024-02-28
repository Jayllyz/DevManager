import domain.developers.DeveloperManager;
import domain.developers.ManageDeveloper;
import infrastructure.developer.DeveloperControllerAdapter;
import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import infrastructure.shared.JavalinExceptionHandler;
import io.javalin.Javalin;
import io.javalin.security.RouteRole;
import shared.exceptions.InvalidAttributeException;
import static io.javalin.apibuilder.ApiBuilder.*;


public class JavalinApp {
    public void start() throws InvalidAttributeException {
        ManageDeveloper developerManager = new DeveloperManager(new DeveloperFakeRepositoryAdapter());

        Javalin app = Javalin.create(/*config*/);

        JavalinExceptionHandler.setApplicationExceptions(app);

        app.get("developer/{email}", DeveloperControllerAdapter::getDeveloperByEmail);
        // Get developer by email

        app.start(8282);
    }
}