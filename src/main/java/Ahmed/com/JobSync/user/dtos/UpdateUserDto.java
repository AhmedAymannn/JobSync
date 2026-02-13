package Ahmed.com.JobSync.user.dtos;

import Ahmed.com.JobSync.common.Enums.ExperienceLevel;

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
