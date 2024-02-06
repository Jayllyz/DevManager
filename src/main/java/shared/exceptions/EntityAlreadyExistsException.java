package shared.exceptions;

public class EntityAlreadyExistsException extends Exception {
    public EntityAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
