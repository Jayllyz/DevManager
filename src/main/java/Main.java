import domain.developers.DeveloperManager;
import domain.developers.DeveloperRepository;
import domain.developers.ManageDeveloper;
import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import infrastructure.developer.TestControllerAdapter;
import io.javalin.Javalin;
import shared.exceptions.InvalidAttributeException;


public class Main {

    // CREATE THE RIGHT SIDE ( DRIVEN )
//    DeveloperRepository devRepository = new DeveloperFakeRepositoryAdapter();
//
//    // CREATE HEXAGON
//    ManageDeveloper manageDeveloper = new DeveloperManager(devRepository);
//
//    // CREATE THE LEFT SIDE ( DRIVING SIDE )
//    TestControllerAdapter devAdapter = new TestControllerAdapter(manageDeveloper);


    public Main() throws InvalidAttributeException {

    }
}
