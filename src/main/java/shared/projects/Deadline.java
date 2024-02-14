package shared.projects;

import java.time.LocalDate;
import shared.exceptions.InvalidAttributeException;

public class Deadline {
    private LocalDate deadline;

    public Deadline(LocalDate deadline) throws InvalidAttributeException {
        if (deadline == null) {
            throw new InvalidAttributeException("Deadline of project cannot be null");
        }

        if (deadline.isBefore(LocalDate.now())) {
            throw new InvalidAttributeException("Deadline of project cannot be in the past");
        }

        this.deadline = deadline;
    }

    public LocalDate toDate() {
        return deadline;
    }
}
