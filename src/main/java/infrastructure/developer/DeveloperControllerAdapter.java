package infrastructure.developer;

import domain.developers.Developer;
import domain.developers.DeveloperManager;
import domain.developers.ManageDeveloper;
import infrastructure.developer.DTO.DeveloperDTO;
import infrastructure.developer.DTO.DeveloperMapper;
import shared.Skill;
import shared.developers.Email;
import io.javalin.http.Context;
import shared.exceptions.EntityAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

public class DeveloperControllerAdapter {

    private static final ManageDeveloper developerManager = new DeveloperManager(
            new DeveloperFakeRepositoryAdapter()
    );

    public static void getAllDevelopers(Context ctx) {
        List<Developer> developers = developerManager.getAllDevelopers();

        List<DeveloperDTO> developersDTOS = new ArrayList<>();

        for(Developer developer : developers) {
            DeveloperDTO developerDTO = DeveloperMapper.mapDeveloperToDTO(developer);
            developersDTOS.add(developerDTO);
        }

        ctx.json(developersDTOS);
    }

    public static void getDeveloperByEmail(Context ctx) {
        String email = ctx.pathParam("email");
        Developer developer = developerManager.getDeveloperByMail(new Email(email));
        DeveloperDTO developerDTO = DeveloperMapper.mapDeveloperToDTO(developer);
        ctx.json(developerDTO);
    }

    public static void getAllDevelopersBySkill(Context ctx) {
        String skill = ctx.pathParam("skill");
        skill = skill.toUpperCase();
        Skill skillEnum;

        try {
            skillEnum = Skill.valueOf(skill);
        } catch (IllegalArgumentException e) {
            ctx.status(400);
            ctx.json("{ \"status\": 400, \"message\": \"Invalid skill\" }");
            return;
        }

        List<Developer> developers = developerManager.getAllDevelopersBySkill(skillEnum);
        List<DeveloperDTO> developersDTOS = new ArrayList<>();

        for(Developer developer : developers) {
            DeveloperDTO developerDTO = DeveloperMapper.mapDeveloperToDTO(developer);
            developersDTOS.add(developerDTO);
        }

        ctx.json(developersDTOS);
    }

    public static void deleteDeveloper(Context ctx) {
        String email = ctx.pathParam("email");
        Email result = developerManager.removeDeveloper(new Email(email));
        if(result == null) {
            ctx.status(404);
            ctx.json("{ \"status\": 404, \"message\": \"Developer not found\" }");
            return;
        }
        ctx.json("{ \"status\": 200, \"message\": \"Developer deleted successfully\" }");
        ctx.status(200);
    }
    
}
