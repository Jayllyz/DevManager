package shared;

import shared.exceptions.InvalidAttributeException;

public enum Status {
    WAITING,
    IN_PROGRESS,
    DONE,
    CANCELLED;

    private int status;

    public static Status fromString(String status) throws InvalidAttributeException {

        if(status.equals("waiting")){
            return WAITING;
        }
        if(status.equals("in progress")) {
            return IN_PROGRESS;
        }
        if(status.equals("done")){
            return DONE;
        }

        if(status.equals("cancelled")){
            return CANCELLED;
        }

        throw new InvalidAttributeException("Status must be waiting, in progress, done or cancelled");

    }
}
