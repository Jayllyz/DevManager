package domain.developers;

public interface DeveloperRepository {
    Developer createDeveloper(Developer developer);
    Developer getDeveloperByMail(String email);
}
