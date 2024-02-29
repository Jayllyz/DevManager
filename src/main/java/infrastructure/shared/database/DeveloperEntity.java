package infrastructure.shared.database;

import jakarta.persistence.*;
import org.jetbrains.annotations.Nullable;
import shared.Skill;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "developer", schema = "public", catalog = "devManager")
public class DeveloperEntity {
    @Id
    @Column(name = "email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "last_name")
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "first_name")
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ManyToMany
    @JoinTable(
            name = "developer_skills",
            joinColumns = @JoinColumn(name = "dev_email"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<SkillEntity> skills;

    public List<SkillEntity> skills() {
        return skills;
    }

    @ManyToMany
    @JoinTable(
            name = "developer_skills",
            joinColumns = @JoinColumn(name = "dev_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<ProjectEntity> projects;

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperEntity that = (DeveloperEntity) o;
        return Objects.equals(email, that.email) && Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, lastName, firstName);
    }
}
