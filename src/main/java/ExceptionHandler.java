import io.javalin.http.Context;
import shared.exceptions.EntityNotFoundException;
import shared.exceptions.InvalidAttributeException;

public class ExceptionHandler {
    public static void handleException(Exception e, Context ctx) {
        int statusCode;
        String message = e.getMessage();

        if (e instanceof EntityNotFoundException) {
            statusCode = 404;
        } else if (e instanceof InvalidAttributeException) {
            statusCode = 400;
        } else {
            statusCode = 500;
            message = "Internal Server Error";
        }

        String json = "{\"status\": " + statusCode + ", \"message\": \"" + message + "\"}";

        ctx.status(statusCode);
        ctx.json(json);
    }
}