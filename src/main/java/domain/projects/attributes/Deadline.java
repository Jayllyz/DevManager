package domain.projects.attributes;

import java.time.LocalDate;
import shared.exceptions.InvalidAttributeException;

public class Deadline {
    private LocalDate deadline;

    public Deadline(LocalDate deadline) throws InvalidAttributeException {
        if (deadline == null) {
            throw new InvalidAttributeException("Deadline cannot be null");
        }

        if (deadline.isBefore(LocalDate.now())) {
            throw new InvalidAttributeException("Deadline cannot be in the past");
        }

        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return deadline;
    }
}
