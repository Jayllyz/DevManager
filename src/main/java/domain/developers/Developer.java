package domain.developers;

import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.developers.Name;
import shared.developers.SkillsByYearsOfExperience;

import java.time.LocalDate;
import java.util.HashMap;

public class Developer {
    private final Name firstName;
    private final Name lastName;
    private final Email emailAddress;
    private final SkillsByYearsOfExperience skills;

    public Developer(Name firstName, Name lastName, Email emailAddress, SkillsByYearsOfExperience skillsByYearsOfExperience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.skills = skillsByYearsOfExperience;
    }

    public String getFirstName() {
        return firstName.toString();
    }

    public String getLastName() {
        return lastName.toString();
    }

    public Email getEmail(){ return this.emailAddress; }

    public String getEmailAddress() {
        return emailAddress.toString();
    }

}
