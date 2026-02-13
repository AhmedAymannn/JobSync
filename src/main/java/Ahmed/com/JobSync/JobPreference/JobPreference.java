package Ahmed.com.JobSync.JobPreference;
import Ahmed.com.JobSync.common.Enums.EmploymentType;
import Ahmed.com.JobSync.common.Enums.ExperienceLevel;
import Ahmed.com.JobSync.common.Enums.EmploymentType;
import Ahmed.com.JobSync.user.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "user_preferences")
public class JobPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id; // Primary key
    @Enumerated(EnumType.STRING)

    @Column(nullable = false)
    private ExperienceLevel experienceLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmploymentType employmentType;

    @Column(length = 255)
    @Nullable
    private String job_title;
    // Example: "backend developer"

    @Column(length = 255)
    @Nullable
    private String location; // Preferred location or "remote"
    private Boolean isRemote = false ; // Remote only?
    @Column
    @Nullable
    private Integer experienceYears ;
    @Column
    @Nullable
    private Double expectedSalary ;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // FK → Users.id

    public JobPreference() {
    }

    public JobPreference(Long id, ExperienceLevel experienceLevel, EmploymentType employmentType,
                         @Nullable String job_title, @Nullable String location, Boolean isRemote,
                         @Nullable Integer experienceYears, @Nullable Double expectedSalary, User user) {
        this.id = id;
        this.experienceLevel = experienceLevel;
        this.employmentType = employmentType;
        this.job_title = job_title;
        this.location = location;
        this.isRemote = isRemote;
        this.experienceYears = experienceYears;
        this.expectedSalary = expectedSalary;
        this.user = user;
    }
    // getters & setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExperienceLevel getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(ExperienceLevel experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    @Nullable
    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(@Nullable String job_title) {
        this.job_title = job_title;
    }

    public Boolean getIsRemote() {
        return isRemote;
    }

    public void setRemoteOnly(Boolean remoteOnly) {
        this.isRemote = isRemote;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public Integer getExperienceYears() {
        return experienceYears;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public Double getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(Double expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
