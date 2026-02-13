package Ahmed.com.JobSync.user.dtos;

import Ahmed.com.JobSync.common.Enums.ExperienceLevel;

import java.time.LocalDateTime;

public class UserResponseDto {
    Long id;
    String email ;
    String name ;
    LocalDateTime createdAt ;
    ExperienceLevel experienceLevel ;

    public UserResponseDto() {
    }

    public UserResponseDto(Long id, String email, String name, LocalDateTime createdAt, ExperienceLevel experienceLevel) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
        this.experienceLevel = experienceLevel;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ExperienceLevel getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(ExperienceLevel experienceLevel) {
        this.experienceLevel = experienceLevel;
    }
}
