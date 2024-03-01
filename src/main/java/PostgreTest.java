import infrastructure.developer.DTO.DeveloperDTO;
import infrastructure.shared.database.DeveloperEntity;
import infrastructure.shared.database.DeveloperSkillsEntity;
import infrastructure.shared.database.EntityMapper;
import infrastructure.shared.database.SkillEntity;
import jakarta.persistence.*;
import org.hibernate.SessionFactory;
import shared.exceptions.EntityNotFoundException;
import shared.exceptions.InvalidAttributeException;

import java.util.List;


public class PostgreTest {


    public static void main(String[] args) throws InvalidAttributeException, EntityNotFoundException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {

//            transaction.begin();

//            DeveloperEntity developer = new DeveloperEntity();
//            developer.setEmail("skilledDeveloper@gmail.com");
//            developer.setFirstName("baba");
//            developer.setLastName("babaa");
//
//            List<String> skillNames = List.of("Java", "Python");
//            String hql = "FROM SkillEntity WHERE name IN :skillNames";
//            Query query = entityManager.createQuery(hql, SkillEntity.class);
//            query.setParameter("skillNames", skillNames);
//
//            List<SkillEntity> skills = query.getResultList();
//
//            entityManager.persist(developer);
//
//
//            System.out.println("TEST COMMIT");
//
//            transaction.commit();
//
//            SkillEntity skill =  entityManager.find(SkillEntity.class,1);
//            System.out.println(skill.getName());


//            String hql = "FROM DeveloperEntity"; // HQL query to select all rows
//            TypedQuery<DeveloperEntity> query = entityManager.createQuery(hql, DeveloperEntity.class);
//            List<DeveloperEntity> skills = query.getResultList();
//            for (DeveloperEntity s : skills) {
//                DeveloperEntity a = s;
//            }
//
            DeveloperEntity devtest =  entityManager.find(DeveloperEntity.class,"john@example.com");
            DeveloperEntity bb = devtest;

            DeveloperDTO devDto = EntityMapper.developerEntityToDTO(bb);
            int a = 3;
//
//            String hql = "FROM DeveloperEntity"; // HQL query to select all rows
//            TypedQuery<DeveloperEntity> query = entityManager.createQuery(hql, DeveloperEntity.class);
//            List<DeveloperEntity> skills = query.getResultList();
//            for (DeveloperEntity s : skills) {
//                DeveloperEntity a = s;
//            }


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