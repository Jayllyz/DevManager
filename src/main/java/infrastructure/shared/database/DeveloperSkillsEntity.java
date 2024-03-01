package infrastructure.shared.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "developer_skills", schema = "public", catalog = "devManager")
public class DeveloperSkillsEntity {

    @Id
    @JoinColumn(name = "dev_email")
    private String dev_email;

    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private SkillEntity skill;

    public SkillEntity getSkill() {
        return skill;
    }

    public void setSkill(SkillEntity skill) {
        this.skill = skill;
    }

    @Column(name = "years_experience")
    private Integer yearsExperience;


    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

}
