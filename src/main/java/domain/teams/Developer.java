package domain.teams;
import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.developers.SkillsByYearsOfExperience;

import java.util.List;

public class Developer {
    private final Email emailAddress;
    private final SkillsByYearsOfExperience skills;

    public Developer(Email emailAddress, SkillsByYearsOfExperience skillsByYearsOfExperience) {
        this.emailAddress = emailAddress;
        this.skills = skillsByYearsOfExperience;
    }

    public Email getEmail(){ return this.emailAddress; }

    public String getEmailAddress() {
        return emailAddress.toString();
    }

    public List<Skill> getSkills() {
        return skills.getSkills();
    }
    public boolean hasSkill(Skill skill) {
        return skills.hasSkill(skill);
    }

    public Experience getSkillExperience(Skill skill) {
        return this.skills.getSkillExperience(skill);
    }

    public Experience getGlobalExperience() {
        return this.skills.getGlobalExperience();
    }

}
