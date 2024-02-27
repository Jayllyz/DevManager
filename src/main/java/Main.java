import domain.developers.Developer;
import domain.developers.DeveloperManager;
import domain.developers.DeveloperRepository;
import infrastructure.developer.DTO.DeveloperMapper;
import infrastructure.developer.DeveloperAdapter;
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
    DeveloperManager developerManager = new DeveloperManager(null);
    DeveloperAdapter devAdapter = new DeveloperAdapter(developerManager);
}
