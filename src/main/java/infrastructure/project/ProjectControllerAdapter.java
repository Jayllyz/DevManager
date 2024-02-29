package infrastructure.project;

import domain.developers.DeveloperManager;
import domain.projects.*;
import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import io.javalin.http.Context;
import shared.exceptions.EntityNotFoundException;
import shared.exceptions.InvalidAttributeException;
import shared.projects.Name;

import java.util.List;

public class ProjectControllerAdapter {
    private static final ManageProject projectManager = new ProjectManager(
            new ProjectFakeRepositoryAdapter(),
            new DeveloperGateway(new DeveloperManager(new DeveloperFakeRepositoryAdapter()))
    );

    public static void getAllProjects(Context ctx) {
        List<Project> projects = projectManager.getAllProjects();
        ctx.status(200);
        ctx.json(projects);
    }

}
