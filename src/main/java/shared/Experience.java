package shared;

public enum Experience {
    JUNIOR,
    SKILLED,
    EXPERT;

    Experience() {
    }

    Experience fromYearsOfExperience(int yearsOfExperience) {
        if(yearsOfExperience <= 3) return JUNIOR;
        if(yearsOfExperience <= 5) return SKILLED;
        return EXPERT;
    }

    private static class Constants {
        public static final int MAX_YEARS_OF_EXPERIENCE = 100;
    }
}
