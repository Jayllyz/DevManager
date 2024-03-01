import infrastructure.developer.driving.DeveloperControllerAdapter;
import infrastructure.project.driving.ProjectControllerAdapter;
import infrastructure.shared.JavalinExceptionHandler;
import infrastructure.shared.router.DeveloperRouter;
import infrastructure.shared.router.ProjectRouter;
import io.javalin.Javalin;
import shared.exceptions.InvalidAttributeException;

public class JavalinApp {
    public void start() throws InvalidAttributeException {

        Javalin app = Javalin.create(/*config*/);
        JavalinExceptionHandler.setApplicationExceptions(app);

        HexagonStarter.createHexagons();

        DeveloperRouter.setRoute(app);
        ProjectRouter.setRoute(app);

        app.start(8282);
    }
}