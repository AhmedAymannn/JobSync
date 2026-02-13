package Ahmed.com.JobSync.common.Enums;

public enum ExperienceLevel {
    // 1. Define Constants with their "Pocket" values
    ENTRY_LEVEL("under_3_years_experience"),
    EXPERIENCED("more_than_3_years_experience"),
    NO_DEGREE("no_degree"),
    NO_EXPERIENCE("no_experience");

    // 2. The Private Field to hold the API string
    private final String apiValue;

    // 3. The Constructor (Runs once for each constant)
    ExperienceLevel(String apiValue) {
        this.apiValue = apiValue;
    }

    // 4. The Getter (This is what JSearchClient calls)
    public String getApiValue() {
        return this.apiValue;
    }
}
