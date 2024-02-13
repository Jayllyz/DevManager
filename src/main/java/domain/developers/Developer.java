package domain.developers;

import shared.developers.Email;
import shared.developers.Name;
import shared.developers.SkillsByYearsOfExperience;

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

    public Name getFirstName() {
        return firstName;
    }

    public Name getLastName() {
        return lastName;
    }

    public Email getEmail(){ return this.emailAddress; }

    public String getEmailAddress() {
        return emailAddress.toString();
    }

    public SkillsByYearsOfExperience getSkills() {
        return skills;
    }
    public boolean hasSkill(Skill skill) {
        return skills.hasSkill(skill);
    }

    public Experience getSkillExperience(Skill skill) {
        return this.skills.getSkillExperience(skill);
    }

}
