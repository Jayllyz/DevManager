package domain.projects;

import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.developers.Name;
import shared.developers.SkillsByYearsOfExperience;

import java.util.List;

public class Developer {
    private final Email emailAddress;
    private final SkillsByYearsOfExperience skills;

    private final Project currentProject;

    public Developer(Email emailAddress, SkillsByYearsOfExperience skillsByYearsOfExperience,Project currentProject) {
        this.emailAddress = emailAddress;
        this.skills = skillsByYearsOfExperience;
        this.currentProject = currentProject;
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