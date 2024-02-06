package shared;

import shared.exceptions.InvalidAttributeException;
public enum Priority {
    NORMAL,
    BEST_EFFORT,
    CRITICAL;

    private int priority;

    public static Priority intToPriority(int priority) {
        try {
            if (priority < 1 || priority > 3) {
                throw new InvalidAttributeException("Priority must be between 1 and 3");
            }
            return Priority.values()[priority - 1];
        } catch (InvalidAttributeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
