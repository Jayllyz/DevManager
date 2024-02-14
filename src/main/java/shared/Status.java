package shared;

import shared.exceptions.InvalidAttributeException;

public enum Status {
    WAITING,
    IN_PROGRESS,
    DONE,
    CANCELLED;

    private int status;

    public static Status intToStatus(int status) {
        try {
            if (status < 1 || status > 4) {
                throw new InvalidAttributeException("Status must be between 1 and 4");
            }
            return Status.values()[status - 1];
        } catch (InvalidAttributeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
