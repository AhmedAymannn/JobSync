package Ahmed.com.JobSync.JobPreference;
import Ahmed.com.JobSync.Enums.ExperienceLevel;
import Ahmed.com.JobSync.Enums.JobType;
import Ahmed.com.JobSync.user.User;
import jakarta.persistence.*;
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
    private JobType jobType;

    @Column(length = 255)
    private String keywords; // Example: "backend developer"

    @Column(length = 255)
    private String location; // Preferred location or "remote"

    private Boolean remote; // Remote only?
    private Integer experienceYears ;

    private Double expectedSalary ;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // FK → Users.id
    // getters & setters

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getRemote() {
        return remote;
    }

    public void setRemote(Boolean remote) {
        this.remote = remote;
    }

    public Integer getExperienceYears() {
        return experienceYears;
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
