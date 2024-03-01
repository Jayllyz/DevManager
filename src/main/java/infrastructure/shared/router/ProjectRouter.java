package infrastructure.shared.router;

import infrastructure.developer.driving.DeveloperControllerAdapter;
import infrastructure.project.driving.ProjectControllerAdapter;
import io.javalin.Javalin;

public class ProjectRouter implements Router {

    public static void setRoute(Javalin app) {
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
    }
}
