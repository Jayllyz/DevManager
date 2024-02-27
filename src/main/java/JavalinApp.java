import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import domain.developers.Developer;
import domain.developers.DeveloperManager;
import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import io.javalin.Javalin;
import shared.developers.Email;
import shared.exceptions.EntityNotFoundException;
import shared.exceptions.InvalidAttributeException;

public class JavalinApp {
    public void start() throws InvalidAttributeException {
        // Modifie le repo avec le vrai une fois que c'est mis en place
        DeveloperManager developerManager = new DeveloperManager(new DeveloperFakeRepositoryAdapter());

        var app = Javalin.create().start(5777);

        app.get("/developer/{email}", ctx -> {
            String emailString = ctx.pathParam("email");
            TestClass test = new TestClass("testing");
            try {
                Email email = new Email(emailString);
                Developer developer = developerManager.getDeveloperByMail(email);
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String json = ow.writeValueAsString(developer);
                ctx.result(json);
            } catch (Exception e) {
                ExceptionHandler.handleException(e, ctx);
            }
        });
    }
}