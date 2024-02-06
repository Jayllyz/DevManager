package shared;

import shared.exceptions.InvalidAttributeException;

public enum Experience {
    JUNIOR,
    SKILLED,
    EXPERT;

    private int yearsOfExperience;

    public static Experience fromYearsOfExperience(int yearsOfExperience) throws InvalidAttributeException {
        if(yearsOfExperience < 0) throw new InvalidAttributeException("years of experience can't have a negative value");
        Experience experience;

        if(yearsOfExperience <= 3) {
            experience = Experience.JUNIOR;
        } else if(yearsOfExperience <= 5) {
            experience = Experience.SKILLED;
        } else {
            experience = Experience.EXPERT;
        }

        experience.yearsOfExperience = yearsOfExperience;
        return experience;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    private static class Constants {
        public static final int MAX_YEARS_OF_EXPERIENCE = 100;
    }
}
