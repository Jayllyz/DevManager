package shared.exceptions;

public class NoEntityFoundException extends Exception {
    public NoEntityFoundException(String errorMessage) {
        super(errorMessage);
    }
}
