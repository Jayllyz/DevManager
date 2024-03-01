import infrastructure.developer.DeveloperControllerAdapter;
import infrastructure.project.ProjectControllerAdapter;
import infrastructure.shared.JavalinExceptionHandler;
import io.javalin.Javalin;
import shared.exceptions.InvalidAttributeException;

public class JavalinApp {
    public void start() throws InvalidAttributeException {
        Javalin app = Javalin.create(/*config*/);

        JavalinExceptionHandler.setApplicationExceptions(app);

        // Developer Endpoints
        // GET
        app.get("developer/email/{email}", DeveloperControllerAdapter::getDeveloperByEmail);
        app.get("developer/all/", DeveloperControllerAdapter::getAllDevelopers);
        app.get("developer/skill/{skill}", DeveloperControllerAdapter::getAllDevelopersBySkill);
        app.get("developer/skillAndExperience/", DeveloperControllerAdapter::getAllDevelopersBySkillAndExperience);

        // POST
        app.post("developer/", DeveloperControllerAdapter::createDeveloper);

        // DELETE
        app.delete("developer/{email}", DeveloperControllerAdapter::deleteDeveloper);

        // Project Endpoints
        // GET
        app.get("project/name/{name}", ProjectControllerAdapter::getProjectByName);
        app.get("project/all/", ProjectControllerAdapter::getAllProjects);
        app.get("project/status/{status}", ProjectControllerAdapter::getProjectsByStatus);
        app.get("project/nextStarting/", ProjectControllerAdapter::getNextStartingProject);

        // POST
        app.post("project/", ProjectControllerAdapter::createProject);
        app.post("project/{name}/addDeveloper/", ProjectControllerAdapter::addDeveloperToProject);

        // PATCH
        app.patch("project/{name}/postpone/{startDate}", ProjectControllerAdapter::postponeProject);

        // DELETE
        app.delete("project/{name}", ProjectControllerAdapter::deleteProject);


        app.start(8282);
    }
}