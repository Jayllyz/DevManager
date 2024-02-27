import domain.developers.Developer;
import io.javalin.Javalin;
import shared.developers.Email;
import shared.exceptions.EntityNotFoundException;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(/*config*/)
                .get("/", ctx -> ctx.result("Hello World"))
                .start(5777);
    }
}
