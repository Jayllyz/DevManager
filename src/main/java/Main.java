import domain.developers.DeveloperManager;
import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import shared.exceptions.InvalidAttributeException;


public class Main {

    public static void main(String[] args) throws InvalidAttributeException {
        DeveloperManager developerManager = new DeveloperManager(new DeveloperFakeRepositoryAdapter());
        String emailString = "johndoe@gmail.com";
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
