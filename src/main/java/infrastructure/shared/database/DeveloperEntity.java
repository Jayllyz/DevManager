package infrastructure.shared.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.jetbrains.annotations.Nullable;
import shared.Skill;

import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(mappedBy = "dev_email")
    Set<DeveloperSkillsEntity> skillsEntities;
    public Set<DeveloperSkillsEntity> getSkillsEntities() {
        return skillsEntities;
    }

    public void setSkillsEntities(Set<DeveloperSkillsEntity> skillsEntities) {
        this.skillsEntities = skillsEntities;
    }

    @ManyToMany
    @JoinTable(
            name = "team",
            joinColumns = @JoinColumn(name = "dev_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<ProjectEntity> projects;

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    @JsonIgnoreProperties({"developer"})
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
