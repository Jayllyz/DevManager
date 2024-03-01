import shared.exceptions.EntityNotFoundException;
import shared.exceptions.InvalidAttributeException;


public class Main {

    public static void main(String[] args) throws InvalidAttributeException, EntityNotFoundException {
        try {
            new JavalinApp().start();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
