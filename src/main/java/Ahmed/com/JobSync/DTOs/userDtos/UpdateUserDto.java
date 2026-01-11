package Ahmed.com.JobSync.DTOs.userDtos;

import Ahmed.com.JobSync.Enums.ExperienceLevel;

public class UpdateUserDto {

    private String name ;
    private ExperienceLevel experienceLevel ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExperienceLevel getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(ExperienceLevel experienceLevel) {
        this.experienceLevel = experienceLevel;
    }


}
