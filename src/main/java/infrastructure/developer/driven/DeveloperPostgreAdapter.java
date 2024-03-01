package infrastructure.developer.driven;

import domain.developers.Developer;
import domain.developers.DeveloperRepository;
import infrastructure.shared.database.DeveloperEntity;
import infrastructure.shared.database.EntityMapper;
import jakarta.persistence.*;
import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.exceptions.EntityNotFoundException;
import shared.projects.Name;

import java.util.ArrayList;
import java.util.List;

public class DeveloperPostgreAdapter implements DeveloperRepository {

    private EntityManager entityManager;
    public DeveloperPostgreAdapter() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    @Override
    public Developer createDeveloper(Developer developer) {
        return null;
    }

    @Override
    public Developer getDeveloperByMail(Email email) throws EntityNotFoundException {

        try {
            DeveloperEntity developerEntity =  entityManager.find(DeveloperEntity.class,email.toString());
            return EntityMapper.developerEntityToDomain(developerEntity);
        } catch (NullPointerException e) {
            throw new EntityNotFoundException("No developer found with this email");
        }

    }

    @Override
    public void removeDeveloper(Email email) {

    }

    @Override
    public List<Developer> getAllDevelopers() {
        try {
            String hql = "FROM DeveloperEntity";
            TypedQuery<DeveloperEntity> query = entityManager.createQuery(hql, DeveloperEntity.class);
            List<DeveloperEntity> developersEntities = query.getResultList();
            return mapDevelopers(developersEntities);

        } catch (NullPointerException e) {
            throw new EntityNotFoundException("Server error " + e.getMessage());
        }
    }

    @Override
    public List<Developer> getAllDevelopersBySkill(Skill skill) {
        String hql = "FROM DeveloperEntity d JOIN d.skillsEntities s WHERE s.skill.name = :skillName";
        TypedQuery<DeveloperEntity> query = entityManager.createQuery(hql,DeveloperEntity.class);
        query.setParameter("skillName",skill.name().toUpperCase());
        List<DeveloperEntity> developerEntities = query.getResultList();
        return mapDevelopers(developerEntities);

    }

    @Override
    public List<Developer> getAllDevelopersBySkillAndExperience(Skill skill, Experience experience) {

        String hql = """
                FROM DeveloperEntity d JOIN d.skillsEntities s\s
                WHERE s.skill.name = :skillName\s
                AND ((:yearsOfExperience <= 3)\s
                OR (:yearsOfExperience > 3 AND s.yearsExperience <= 5)\s
                OR (:yearsOfExperience > 5))""";

        TypedQuery<DeveloperEntity> query = entityManager.createQuery(hql,DeveloperEntity.class);
        query.setParameter("skillName",skill.name().toUpperCase());
        query.setParameter("yearsOfExperience",experience.getYearsOfExperience());
        List<DeveloperEntity> developerEntities = query.getResultList();
        return mapDevelopers(developerEntities);
    }

    @Override
    public List<Developer> getAvailableDevelopersForProject(Name name) throws EntityNotFoundException {
        return null;
    }

    private List<Developer> mapDevelopers(List<DeveloperEntity> developerEntities) {
        List<Developer> developers = new ArrayList<>();

        for (DeveloperEntity developerEntity : developerEntities) {
            Developer developer = EntityMapper.developerEntityToDomain(developerEntity);
            developers.add(developer);
        }

        return developers;
    }
}
