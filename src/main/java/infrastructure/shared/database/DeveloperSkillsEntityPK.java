package infrastructure.shared.database;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class DeveloperSkillsEntityPK implements Serializable {
    @Column(name = "dev_email")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String devEmail;
    @Column(name = "skill_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skillId;

    public String getDevEmail() {
        return devEmail;
    }

    public void setDevEmail(String devEmail) {
        this.devEmail = devEmail;
    }

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
        DeveloperSkillsEntityPK that = (DeveloperSkillsEntityPK) o;
        return skillId == that.skillId && Objects.equals(devEmail, that.devEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(devEmail, skillId);
    }
}
