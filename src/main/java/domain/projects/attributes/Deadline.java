package domain.projects.attributes;

import java.time.LocalDate;

public class Deadline {
    private LocalDate deadline;

    public Deadline(LocalDate deadline) {
        if (deadline == null) {
            throw new IllegalArgumentException("Deadline cannot be null");
        }

        if (deadline.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Deadline cannot be in the past");
        }

        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return deadline;
    }
}
