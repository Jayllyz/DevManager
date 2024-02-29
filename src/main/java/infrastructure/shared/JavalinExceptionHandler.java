package infrastructure.shared;

import io.javalin.Javalin;
import io.javalin.http.Context;
import shared.exceptions.EntityAlreadyExistsException;
import shared.exceptions.EntityNotFoundException;
import shared.exceptions.InvalidAttributeException;

public class JavalinExceptionHandler {

    public static void setApplicationExceptions(Javalin app) {

        app.exception(EntityNotFoundException.class, (e, ctx) -> {
            String jsonError = makeJsonError(404,e.getMessage());
            ctx.json(jsonError)
                    .status(404);
        });

        app.exception(InvalidAttributeException.class, (e, ctx) -> {
            String jsonError = makeJsonError(404,e.getMessage());
            ctx.json(jsonError).
                    status(400);
        });

        app.exception(IllegalArgumentException.class, (e, ctx) -> {
            String jsonError = makeJsonError(404,e.getMessage());
            ctx.json(jsonError).
                    status(400);
        });

        app.exception(EntityAlreadyExistsException.class, (e, ctx) -> {
            String jsonError = makeJsonError(404,e.getMessage());
            ctx.json(jsonError).
                    status(409);
        });

    }

    private static String makeJsonError(int statusCode, String errorMessage) {
        return "{\"status\": " + statusCode + ", \"message\": \"" + errorMessage + "\"}";
    }
}