package shared.projects;

import shared.exceptions.InvalidAttributeException;
import java.time.LocalDate;

public class StartDate {
    private LocalDate projectStart;

    public StartDate(LocalDate projectStart) throws InvalidAttributeException{
        if (projectStart == null) {
            throw new InvalidAttributeException("Start date of project cannot be null");
        }
        if(projectStart.isBefore(LocalDate.now())) {
            throw new InvalidAttributeException("Start of project cannot be in the past !");
        }

        this.projectStart = projectStart;
    }

    public LocalDate toDate() {
        return this.projectStart;
    }
}
