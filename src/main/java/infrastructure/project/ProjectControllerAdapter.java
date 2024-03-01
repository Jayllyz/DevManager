package infrastructure.project;

import domain.developers.DeveloperManager;
import domain.projects.*;
import domain.teams.TeamManager;
import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import infrastructure.team.TeamFakeRepositoryAdapter;
import io.javalin.http.Context;

import java.util.List;

public class ProjectControllerAdapter {
    private static final ManageProject projectManager = new ProjectManager(
            new ProjectFakeRepositoryAdapter(),
            new DeveloperGateway(new DeveloperManager(new DeveloperFakeRepositoryAdapter())),
            new TeamGateway(new TeamManager(new TeamFakeRepositoryAdapter()))
    );

    public static void getAllProjects(Context ctx) {
        List<Project> projects = projectManager.getAllProjects();
        ctx.status(200);
        ctx.json(projects);
    }

}
