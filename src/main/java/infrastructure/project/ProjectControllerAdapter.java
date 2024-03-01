package infrastructure.project;

import domain.developers.DeveloperManager;
import domain.projects.*;
import domain.teams.TeamManager;
import infrastructure.developer.DeveloperFakeRepositoryAdapter;
import infrastructure.project.DTO.*;
import infrastructure.team.TeamFakeRepositoryAdapter;
import io.javalin.http.Context;
import shared.Status;
import shared.projects.Name;

import java.util.ArrayList;
import java.util.List;

public class ProjectControllerAdapter {
    private static final ManageProject projectManager = new ProjectManager(
            new ProjectFakeRepositoryAdapter(),
            new DeveloperGateway(new DeveloperManager(new DeveloperFakeRepositoryAdapter())),
            new TeamGateway(new TeamManager(new TeamFakeRepositoryAdapter()))
    );

    public static void getProjectByName(Context ctx) {
        String name = ctx.pathParam("name");
        Project project = projectManager.getProjectByName(new Name(name));
        Team team = projectManager.getTeamForProject(project);
        List<Developer> developers = team.getDevelopers();

        List<DeveloperDTO> developersDTOS = new ArrayList<>();
        for(Developer developer : developers) {
            DeveloperDTO developerDTO = DeveloperMapper.mapDeveloperToDTO(developer);
            developersDTOS.add(developerDTO);
        }

        ProjectDTO projectDTO = ProjectMapper.mapProjectToDTO(project, developersDTOS);
        ctx.status(200);
        ctx.json(projectDTO);
    }

    public static void getAllProjects(Context ctx) {
        List<Project> projects = projectManager.getAllProjects();
        List<ProjectDTO> projectsDTOS = new ArrayList<>();
        for(Project project : projects) {
            Team team = projectManager.getTeamForProject(project);
            List<Developer> developers = team.getDevelopers();

            List<DeveloperDTO> developersDTOS = new ArrayList<>();
            for(Developer developer : developers) {
                DeveloperDTO developerDTO = DeveloperMapper.mapDeveloperToDTO(developer);
                developersDTOS.add(developerDTO);
            }

            ProjectDTO projectDTO = ProjectMapper.mapProjectToDTO(project, developersDTOS);
            projectsDTOS.add(projectDTO);
        }

        ctx.status(200);
        ctx.json(projectsDTOS);
    }

    public static void getProjectsByStatus(Context ctx) {
        String status = ctx.pathParam("status");
        status = status.toUpperCase();
        List<Project> projects = projectManager.listProjectByStatus(Status.valueOf(status));
        List<ProjectDTO> projectsDTOS = new ArrayList<>();
        for(Project project : projects) {
            Team team = projectManager.getTeamForProject(project);
            List<Developer> developers = team.getDevelopers();

            List<DeveloperDTO> developersDTOS = new ArrayList<>();
            for(Developer developer : developers) {
                DeveloperDTO developerDTO = DeveloperMapper.mapDeveloperToDTO(developer);
                developersDTOS.add(developerDTO);
            }

            ProjectDTO projectDTO = ProjectMapper.mapProjectToDTO(project, developersDTOS);
            projectsDTOS.add(projectDTO);
        }

        ctx.status(200);
        ctx.json(projectsDTOS);
    }

    public static void deleteProject(Context ctx) {
        String name = ctx.pathParam("name");
        Project project = projectManager.getProjectByName(new Name(name));
        projectManager.deleteProject(project);

        ctx.json("{ \"status\": 200, \"message\": \"Project successfully deleted\" }");
        ctx.status(200);
    }

}
