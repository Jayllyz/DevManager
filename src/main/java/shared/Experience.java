package shared;

import shared.exceptions.InvalidAttributeException;

import static shared.Experience.Constants.MAX_YEARS_OF_EXPERIENCE;

public enum Experience {
    JUNIOR(1),
    SKILLED(3),
    EXPERT(5);

    private int yearsOfExperience;

    Experience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }


    public static Experience fromYearsOfExperience(int yearsOfExperience) throws InvalidAttributeException {
        if(yearsOfExperience < 0) throw new InvalidAttributeException("years of experience can't have a negative value");
        Experience experience;

        if(yearsOfExperience <= 3) {
            experience = Experience.JUNIOR;
        } else if(yearsOfExperience <= 5) {
            experience = Experience.SKILLED;
        } else{
            experience = Experience.EXPERT;
        }

        experience.yearsOfExperience = yearsOfExperience > MAX_YEARS_OF_EXPERIENCE ? 100 : yearsOfExperience;
        return experience;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    static class Constants {
        public static final int MAX_YEARS_OF_EXPERIENCE = 100;
    }
}
