package Ahmed.com.JobSync.common.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EmploymentType {

    FULL_TIME("FULLTIME"),
    PART_TIME("PARTTIME"),
    CONTRACTOR("CONTRACTOR"),
    INTERN("INTERN"),
    OTHER("OTHER");

    private final String apiValue;

    EmploymentType(String apiValue) {
        this.apiValue = apiValue;
    }

    @JsonValue
    public String getApiValue() {
        return apiValue;
    }

    @JsonCreator
    public static EmploymentType fromString(String value) {
        if (value == null || value.isBlank()) return OTHER;
        String input = value.toUpperCase().trim();
        if (input.contains("FULL")) return FULL_TIME;
        if (input.contains("PART")) return PART_TIME;
        if (input.contains("CONTRACT") || input.contains("TEMP")) return CONTRACTOR;
        if (input.contains("INTERN")) return INTERN;
        return OTHER;
    }
}