import infrastructure.shared.database.DeveloperEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import shared.exceptions.EntityNotFoundException;
import shared.exceptions.InvalidAttributeException;


public class PostgreTest {

    public static void main(String[] args) throws InvalidAttributeException, EntityNotFoundException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {

            transaction.begin();

            DeveloperEntity developer = new DeveloperEntity();
            developer.setEmail("mydevelopertest@gmail.com");
            developer.setFirstName("baba");
            developer.setLastName("babaa");
            entityManager.persist(developer);

            transaction.commit();

        } catch (Exception e) {
            System.out.println("EXCEPTION");
            System.out.println(e.getMessage());
            // Handle any exceptions here
            // ...
        } finally {
            if(transaction.isActive()) transaction.rollback();
        }
        entityManager.close();
        entityManagerFactory.close();

    }

}