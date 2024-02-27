package infrastructure.developer;

import domain.developers.Developer;
import domain.developers.DeveloperManager;
import domain.developers.ManageDeveloper;
import infrastructure.developer.DTO.DeveloperDTO;
import infrastructure.developer.DTO.DeveloperMapper;
import shared.developers.Email;
import io.javalin.http.Context;

public class DeveloperControllerAdapter {

    private static final ManageDeveloper developerManager = new DeveloperManager(
            new DeveloperFakeRepositoryAdapter()
    );


    public static void getDeveloperByEmail(Context ctx) {
        String email = ctx.pathParam("email");
        Developer developer = developerManager.getDeveloperByMail(new Email(email));
        DeveloperDTO developerDTO = DeveloperMapper.mapDeveloperToDTO(developer);
        ctx.json(developerDTO);
    }

}
