import domain.developers.DeveloperManager;
import domain.developers.DeveloperRepository;
import domain.developers.ManageDeveloper;
import domain.developers.ManageDeveloperProject;
import domain.projects.*;
import domain.teams.ManageTeam;
import domain.teams.ManageTeamProject;
import domain.teams.TeamManager;
import domain.teams.TeamRepository;
import infrastructure.developer.driving.DeveloperControllerAdapter;
import infrastructure.developer.driven.DeveloperPostgreAdapter;
import infrastructure.developer.driving.DeveloperGateway;
import infrastructure.project.driving.ProjectControllerAdapter;
import infrastructure.project.driven.ProjectFakeRepositoryAdapter;
import infrastructure.team.driven.TeamGateway;
import infrastructure.team.driving.TeamFakeRepositoryAdapter;

public class HexagonStarter {
    public static void createHexagons() {
        TeamRepository teamFakeRepositoryAdapter = new TeamFakeRepositoryAdapter();
        ProjectRepository projectFakeRepositoryAdapter = new ProjectFakeRepositoryAdapter();
        DeveloperRepository postgreAdapter = new DeveloperPostgreAdapter();

        // CREATE THE HEXAGONS
        // GATEWAY FOR PROJECT TO COMMUNICATE WITH DEVELOPER HEXAGON
        ManageDeveloper developerManager = new DeveloperManager(postgreAdapter);
        DeveloperManagement developerGateway = new DeveloperGateway((ManageDeveloperProject) developerManager);

        // GATEWAY FOR PROEJCT TO COMMUNICATE WITH TEAM HEXAGON
        ManageTeam teamManager = new TeamManager(teamFakeRepositoryAdapter);
        TeamManagement teamGateway = new TeamGateway((ManageTeamProject) teamManager);

        ManageProject projectManager = new ProjectManager(projectFakeRepositoryAdapter, (DeveloperManagement) developerGateway,(TeamManagement) teamGateway);

        // CREATE THE LEFT SIDE (DRIVING)

        DeveloperControllerAdapter.initialize(developerManager);
        ProjectControllerAdapter.initialize(projectManager);
    }
}
