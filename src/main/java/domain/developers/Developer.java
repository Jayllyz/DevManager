package domain.developers;

import domain.Skill;
import shared.developers.Name;

import java.time.LocalDate;
import java.util.HashMap;

public class Developer {
    private final String name;

    private final String emailAddress;
    private HashMap<Skill, Integer> skills;
    public Developer(String name, String emailAdress, HashMap<Skill, Integer> skills, LocalDate lastTransfer) {
        this.name = name;
        this.emailAddress = emailAdress;
        this.skills = skills;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getName() {
        return name;
    }
}
