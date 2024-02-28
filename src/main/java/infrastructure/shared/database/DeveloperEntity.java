package infrastructure.shared.database;

import jakarta.persistence.*;
import org.jetbrains.annotations.Nullable;

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

    @Basic
    @Column(name = "new_column")
    private int newColumn;

    public int getNewColumn() {
        return newColumn;
    }

    public void setNewColumn(int newColumn) {
        this.newColumn = newColumn;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperEntity that = (DeveloperEntity) o;
        return newColumn == that.newColumn && Objects.equals(email, that.email) && Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, lastName, firstName, newColumn);
    }
}
