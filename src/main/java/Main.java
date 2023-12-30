import domain.developers.DeveloperManager;
import domain.developers.DeveloperRepository;
import domain.developers.ManageDeveloper;
import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import infrastructure.developer.TestControllerAdapter;

public class Main {

    // CREATE THE RIGHT SIDE ( DRIVEN )
    DeveloperRepository devRepository = new DeveloperFakeRepositoryAdapter();

    // CREATE HEXAGON
    ManageDeveloper manageDeveloper = new DeveloperManager(devRepository);

    // CREATE THE LEFT SIDE ( DRIVING SIDE )
    TestControllerAdapter devAdapter = new TestControllerAdapter(manageDeveloper);



}
