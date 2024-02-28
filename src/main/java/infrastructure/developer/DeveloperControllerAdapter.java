package infrastructure.developer;

import domain.developers.Developer;
import domain.developers.DeveloperManager;
import domain.developers.ManageDeveloper;
import infrastructure.developer.DTO.DeveloperDTO;
import infrastructure.developer.DTO.DeveloperMapper;
import shared.developers.Email;
import io.javalin.http.Context;

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

}
