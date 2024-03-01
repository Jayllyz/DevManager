package infrastructure.project.driving;

import domain.projects.*;
import domain.projects.attributes.Description;
import infrastructure.project.DTO.*;
import io.javalin.http.Context;
import shared.Priority;
import shared.Skill;
import shared.Status;
import shared.exceptions.EntityAlreadyExistsException;
import shared.projects.Deadline;
import shared.projects.Name;
import shared.projects.SkillStack;
import shared.projects.StartDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectControllerAdapter {

    private static ManageProject manageProject;

    public static void initialize(ManageProject manageProject) {
        ProjectControllerAdapter.manageProject = manageProject;
    }

    public static void getProjectByName(Context ctx) {
        String name = ctx.pathParam("name");
        Project project = manageProject.getProjectByName(new Name(name));
        Team team = manageProject.getTeamForProject(project);
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
        List<Project> projects = manageProject.getAllProjects();
        List<ProjectDTO> projectsDTOS = new ArrayList<>();
        for(Project project : projects) {
            Team team = manageProject.getTeamForProject(project);
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
        List<Project> projects = manageProject.listProjectByStatus(Status.valueOf(status));
        List<ProjectDTO> projectsDTOS = new ArrayList<>();
        for(Project project : projects) {
            Team team = manageProject.getTeamForProject(project);
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

    public static void getNextStartingProject(Context ctx) {
        Project project = manageProject.getNextStartingProject();
        Team team = manageProject.getTeamForProject(project);
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

    public static void createProject(Context ctx) throws EntityAlreadyExistsException {
        ProjectDTO projectDTO = ctx.bodyAsClass(ProjectDTO.class);

        Name name = new Name(projectDTO.getName());
        Priority priority;
        try {
            priority = Priority.valueOf(projectDTO.getPriority().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid priority");
        }
        Description description = new Description(projectDTO.getDescription());
        StartDate start = new StartDate(projectDTO.getStart());
        Deadline deadline = new Deadline(projectDTO.getDeadline());
        SkillStack skillStack = new SkillStack();

        for(Map.Entry<Skill, Integer> entry : projectDTO.getSkillStack().entrySet()) {
            skillStack.put(entry.getKey(), entry.getValue());
        }

        Project project = manageProject.createProject(name, priority, description, start, deadline, skillStack);
        Team team = manageProject.getTeamForProject(project);
        List<Developer> developers = team.getDevelopers();

        List<DeveloperDTO> developersDTOS = new ArrayList<>();
        for(Developer developer : developers) {
            DeveloperDTO developerDTO = DeveloperMapper.mapDeveloperToDTO(developer);
            developersDTOS.add(developerDTO);
        }

        ProjectDTO returnedProjectDTO = ProjectMapper.mapProjectToDTO(project, developersDTOS);
        ctx.status(201);
        ctx.json(returnedProjectDTO);
    }

    public static void addDeveloperToProject(Context ctx) {
        String name = ctx.pathParam("name");
        Project project = manageProject.getProjectByName(new Name(name));

        ProjectDTO projectDTO = ctx.bodyAsClass(ProjectDTO.class);
        List<DeveloperDTO> developersDTO = projectDTO.getTeam();

        List<Developer> developers = new ArrayList<>();
        for(DeveloperDTO developerDTO : developersDTO) {
            developers.add(DeveloperMapper.mapDTOToDeveloper(developerDTO));
        }

        Team team = manageProject.addDeveloperToProject(developers, project);
        List<Developer> teamDevelopers = team.getDevelopers();

        List<DeveloperDTO> developersDTOS = new ArrayList<>();
        for(Developer developer : teamDevelopers) {
            DeveloperDTO developerDTO = DeveloperMapper.mapDeveloperToDTO(developer);
            developersDTOS.add(developerDTO);
        }

        ProjectDTO returnedProjectDTO = ProjectMapper.mapProjectToDTO(project, developersDTOS);
        ctx.status(200);
        ctx.json(returnedProjectDTO);
    }

    public static void postponeProject(Context ctx) {
        String name = ctx.pathParam("name");
        String startDate = ctx.pathParam("startDate");
        Project project = manageProject.getProjectByName(new Name(name));
        LocalDate startDateParsed = LocalDate.parse(startDate);
        Project postponedProject = manageProject.postponeProject(project, startDateParsed);

        Team team = manageProject.getTeamForProject(postponedProject);
        List<Developer> developers = team.getDevelopers();

        List<DeveloperDTO> developersDTOS = new ArrayList<>();
        for(Developer developer : developers) {
            DeveloperDTO developerDTO = DeveloperMapper.mapDeveloperToDTO(developer);
            developersDTOS.add(developerDTO);
        }

        ProjectDTO projectDTO = ProjectMapper.mapProjectToDTO(postponedProject, developersDTOS);
        ctx.status(200);
        ctx.json(projectDTO);
    }

    public static void deleteProject(Context ctx) {
        String name = ctx.pathParam("name");
        Project project = manageProject.getProjectByName(new Name(name));
        manageProject.deleteProject(project);

        ctx.json("{ \"status\": 200, \"message\": \"Project successfully deleted\" }");
        ctx.status(200);
    }

}
