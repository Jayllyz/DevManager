package infrastructure.shared.database;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "skills_for_project", schema = "public", catalog = "devManager")
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

    @Id
    @Column(name = "project_id")
    private int project_id;

    public int getProjectId() {
        return project_id;
    }

    public void setProjectId(int projectId) {
        this.project_id = projectId;
    }

}
