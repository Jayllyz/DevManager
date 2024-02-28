package infrastructure.shared.database;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "developer_skills", schema = "public", catalog = "devManager")
public class DeveloperSkillsEntity {
    @Basic
    @Column(name = "years_experience")
    private int yearsExperience;

    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dev_email")
    private String devEmail;

    public String getDevEmail() {
        return devEmail;
    }

    public void setDevEmail(String devEmail) {
        this.devEmail = devEmail;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "skill_id")
    private int skillId;

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperSkillsEntity that = (DeveloperSkillsEntity) o;
        return yearsExperience == that.yearsExperience && skillId == that.skillId && Objects.equals(devEmail, that.devEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearsExperience, devEmail, skillId);
    }
}
