package domain.developers;

import shared.Skill;
import shared.developers.Email;
import shared.developers.Name;

import java.time.LocalDate;
import java.util.HashMap;

public class Developer {
    private final Name firstName;
    private final Name lastName;
    private final Email emailAddress;
    private final HashMap<Skill, Integer> skillsByYearsOfExperience;

    public Developer(Name firstName, Name lastName, Email emailAddress, HashMap<Skill, Integer> skillsByYearsOfExperience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.skillsByYearsOfExperience = skillsByYearsOfExperience;
    }

    public String getFirstName() {
        return firstName.toString();
    }

    public String getLastName() {
        return lastName.toString();
    }

    public String getEmailAddress() {
        return emailAddress.toString();
    }

}
