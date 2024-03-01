package infrastructure.shared.router;

import infrastructure.developer.driving.DeveloperControllerAdapter;
import io.javalin.Javalin;

public class DeveloperRouter implements Router {

    public static void setRoute(Javalin app) {
        app.get("developer/email/{email}", DeveloperControllerAdapter::getDeveloperByEmail);
        app.get("developer/all/", DeveloperControllerAdapter::getAllDevelopers);
        app.get("developer/skill/{skill}", DeveloperControllerAdapter::getAllDevelopersBySkill);
        app.get("developer/skillAndExperience/", DeveloperControllerAdapter::getAllDevelopersBySkillAndExperience);

        // POST
        app.post("developer/", DeveloperControllerAdapter::createDeveloper);

        // DELETE
        app.delete("developer/{email}", DeveloperControllerAdapter::deleteDeveloper);
    }
}
