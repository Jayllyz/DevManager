package infrastructure.developer;

import domain.developers.*;
import infrastructure.developer.DTO.DeveloperDTO;
import infrastructure.developer.DTO.DeveloperMapper;
import infrastructure.developer.driven.DeveloperPostgreAdapter;
import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import io.javalin.http.Context;
import shared.developers.Name;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.EntityAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

public class DeveloperControllerAdapter {

    private static final ManageDeveloper developerManager = new DeveloperManager(
            new DeveloperPostgreAdapter()
    );

    public static void getAllDevelopers(Context ctx) {
        List<Developer> developers = developerManager.getAllDevelopers();

        List<DeveloperDTO> developersDTOS = new ArrayList<>();

        for(Developer developer : developers) {
            DeveloperDTO developerDTO = DeveloperMapper.mapDeveloperToDTO(developer);
            developersDTOS.add(developerDTO);
        }

        ctx.status(200);
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

        skillEnum = Skill.valueOf(skill);

        List<Developer> developers = developerManager.getAllDevelopersBySkill(skillEnum);
        List<DeveloperDTO> developersDTOS = new ArrayList<>();

        for(Developer developer : developers) {
            DeveloperDTO developerDTO = DeveloperMapper.mapDeveloperToDTO(developer);
            developersDTOS.add(developerDTO);
        }

        ctx.status(200);
        ctx.json(developersDTOS);
    }

    public static void getAllDevelopersBySkillAndExperience(Context ctx){
        String jsonBody = ctx.body();

        String skill = ctx.queryParam("skill");
        String experience = ctx.queryParam("experience");

        Skill skillEnum;
        Experience experienceEnum;

        skill = skill.toUpperCase();
        skillEnum = Skill.valueOf(skill);

        experience = experience.toString().toUpperCase();
        experienceEnum = Experience.valueOf(experience);

        List<Developer> developers = developerManager.getAllDevelopersBySkillAndExperience(skillEnum, experienceEnum);
        List<DeveloperDTO> developersDTOS = new ArrayList<>();

        for(Developer developer : developers) {
            DeveloperDTO developerDTO = DeveloperMapper.mapDeveloperToDTO(developer);
            developersDTOS.add(developerDTO);
        }

        ctx.status(200);
        ctx.json(developersDTOS);
    }

    public static void createDeveloper(Context ctx) throws EntityAlreadyExistsException {
        DeveloperDTO developerDTO = ctx.bodyAsClass(DeveloperDTO.class);

        Name firstName = new Name(developerDTO.getFirstName());
        Name lastName = new Name(developerDTO.getLastName());
        Email email = new Email(developerDTO.getEmailAddress());
        SkillsByYearsOfExperience skillsByYearsOfExperience = new SkillsByYearsOfExperience(developerDTO.getSkillByExperience());

        Developer developer = new Developer(firstName, lastName, email, skillsByYearsOfExperience, null);

        developerManager.createDeveloper(developer);

        ctx.status(201);
        ctx.json(developerDTO);
    }

    public static void deleteDeveloper(Context ctx) {
        String email = ctx.pathParam("email");
        Email result = developerManager.removeDeveloper(new Email(email));
        ctx.json("{ \"status\": 200, \"message\": \"Developer deleted successfully\" }");
        ctx.status(200);
    }
    
}
