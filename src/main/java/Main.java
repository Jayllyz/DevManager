import domain.developers.Developer;
import domain.developers.DeveloperManager;
import infrastructure.developer.DTO.DeveloperMapper;
import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import shared.developers.Email;
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

    // CREATE THE RIGHT SIDE ( DRIVEN )
//    DeveloperRepository devRepository = new DeveloperFakeRepositoryAdapter();
//
//    // CREATE HEXAGON
//    ManageDeveloper manageDeveloper = new DeveloperManager(devRepository);
//
//    // CREATE THE LEFT SIDE ( DRIVING SIDE )
//    TestControllerAdapter devAdapter = new TestControllerAdapter(manageDeveloper);
}
