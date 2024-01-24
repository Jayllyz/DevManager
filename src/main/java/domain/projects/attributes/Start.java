package domain.projects.attributes;

import java.time.LocalDate;

public class Start {
    private LocalDate start;

    public Start(LocalDate start) {
        if (start == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }

        this.start = start;
    }

    public LocalDate getStart() {
        return start;
    }
}
