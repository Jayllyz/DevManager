package domain.projects.attributes;

import shared.exceptions.InvalidAttributeException;
import java.time.LocalDate;

public class Start {
    private LocalDate start;

    public Start(LocalDate start) throws InvalidAttributeException{
        if (start == null) {
            throw new InvalidAttributeException("Start date cannot be null");
        }

        this.start = start;
    }

    public LocalDate getStart() {
        return start;
    }
}
