package domain.developers;

import domain.Skill;

import java.time.LocalDate;
import java.util.HashMap;

public class Developer {
    private final String name;
    private final String emailAddress;
    private HashMap<Skill, Integer> skills;
    private LocalDate lastTransfer;
    public Developer(String name, String emailAdress, HashMap<Skill, Integer> skills, LocalDate lastTransfer) {
        this.name = name;
        this.emailAddress = emailAdress;
        this.skills = skills;
        this.lastTransfer = lastTransfer;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getName() {
        return name;
    }
}
