package infrastructure.shared.database;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "skills_for_project", schema = "public", catalog = "devManager")
@IdClass(infrastructure.shared.database.SkillsForProjectEntityPK.class)
public class SkillsForProjectEntity {
    @Basic
    @jakarta.persistence.Column(name = "dev_number")
    private int devNumber;

    public int getDevNumber() {
        return devNumber;
    }

    public void setDevNumber(int devNumber) {
        this.devNumber = devNumber;
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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "project_id")
    private int projectId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillsForProjectEntity that = (SkillsForProjectEntity) o;
        return devNumber == that.devNumber && skillId == that.skillId && projectId == that.projectId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(devNumber, skillId, projectId);
    }
}
