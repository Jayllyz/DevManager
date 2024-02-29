package infrastructure.shared.database;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "developer_skills", schema = "public", catalog = "devManager")
public class DeveloperSkillsEntity {

    @Id
    @ManyToOne
    @MapsId("email")
    @JoinColumn(name = "dev_email")
    private DeveloperEntity developer;

    @Id
    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "skill_id")
    private SkillEntity skill;

    @Column(name = "years_experience")
    private Integer yearsExperience;


    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

}
