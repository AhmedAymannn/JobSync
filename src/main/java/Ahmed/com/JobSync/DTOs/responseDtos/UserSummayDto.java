package Ahmed.com.JobSync.DTOs.responseDtos;

import Ahmed.com.JobSync.Enums.ExperienceLevel;

public class UserSummayDto {
    private String name ;
    private String email ;
    private ExperienceLevel experienceLevel ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ExperienceLevel getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(ExperienceLevel experienceLevel) {
        this.experienceLevel = experienceLevel;
    }
}
