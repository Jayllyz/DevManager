package domain.developers;

import shared.Experience;
import shared.Skill;
import shared.developers.Email;
import shared.developers.Name;
import shared.developers.SkillsByYearsOfExperience;
import shared.exceptions.EntityNotFoundException;

import java.util.List;

public class Developer {
    private final Name firstName;

    private final Name lastName;
    private final Email emailAddress;
    private final SkillsByYearsOfExperience skills;
    private final Projects projects;

    public Developer(Name firstName, Name lastName, Email emailAddress, SkillsByYearsOfExperience skillsByYearsOfExperience, Projects projects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.skills = skillsByYearsOfExperience;
        this.projects = projects;
    }

    public Name getFirstName() {
        return firstName;
    }

    public Name getLastName() {
        return lastName;
    }
    public Boolean isCurrentlyInProject() {
        return projects.hasOngoingProject();
    }

    public Project blablaabla() throws EntityNotFoundException {
        return projects.blablaabla();
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

    public Projects getProjects() {
        return projects;
    }

    public SkillsByYearsOfExperience getSkillsByYearsOfExperience() {
        return skills;
    }
    public Experience getSkillExperience(Skill skill) {
        return this.skills.getSkillExperience(skill);
    }

    public Experience getGlobalExperience() {
        return this.skills.getGlobalExperience();
    }

}
