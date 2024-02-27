package shared;

import shared.exceptions.InvalidAttributeException;
public enum Priority {
    NORMAL,
    BEST_EFFORT,
    CRITICAL;

    private int priority;

    public static Priority fromString(String priority) throws InvalidAttributeException {

            if(priority.equals("normal")){
                return NORMAL;
            }
            if(priority.equals("best-effort")) {
                return BEST_EFFORT;
            }
            if(priority.equals("critical")){
                return CRITICAL;
            }
            throw new InvalidAttributeException("Priority must be normal, best-effort, or critical");
    }
}
