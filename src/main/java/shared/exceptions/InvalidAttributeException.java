package shared.exceptions;

public class InvalidAttributeException extends RuntimeException {

    public InvalidAttributeException(String errorMessage) {
        super(errorMessage);
    }
}
