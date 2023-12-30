package domain.developers;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

public class Developer {
    private final String name;
    private final String emailAdress;
    private HashMap<Skill, Integer> skills;
    private LocalDate lastTransfer;
    public Developer(String name, String emailAdress, HashMap<Skill, Integer> skills, LocalDate lastTransfer) {
        this.name = name;
        this.emailAdress = emailAdress;
        this.skills = skills;
        this.lastTransfer = lastTransfer;
    }

}