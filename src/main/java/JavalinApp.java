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

        var app = Javalin.create(/*config*/);

        JavalinExceptionHandler.setApplicationExceptions(app);

        // Get developer by email
        app.get("/developer/{email}", DeveloperControllerAdapter::getDeveloperByEmail);

        app.start(8282);
    }
}