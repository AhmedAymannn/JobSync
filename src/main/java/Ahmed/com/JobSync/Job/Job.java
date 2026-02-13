package Ahmed.com.JobSync.Job;
import Ahmed.com.JobSync.JobPreference.JobPreference;
import Ahmed.com.JobSync.common.Enums.EmploymentType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String source;
    @Column(nullable = false, unique = true)// "LinkedIn"
    private String externalJobId;
    @Column(nullable = false) // LinkedIn job ID
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false, length = 1000)
    private String jobUrl;
    @Column(name = "employment_type")
    private EmploymentType employmentType;
    @Column(nullable = false)
    private LocalDateTime postedAt;
    private LocalDateTime fetchedAt = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preference_id", nullable = false)
    private JobPreference jobPreference;
    public Job() {
    }

    public Job(Long id, String source, String externalJobId, String title, String companyName, String jobUrl,
               String location, LocalDateTime postedAt, LocalDateTime fetchedAt, EmploymentType employmentType , JobPreference jobPreference) {
        this.id = id;
        this.source = source;
        this.externalJobId = externalJobId;
        this.title = title;
        this.companyName = companyName;
        this.jobUrl = jobUrl;
        this.location = location;
        this.postedAt = postedAt;
        this.fetchedAt = fetchedAt;
        this.jobPreference = jobPreference;
        this.employmentType = employmentType;
    }
    // Getters & Setters


    public Long getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getExternalJobId() {
        return externalJobId;
    }

    public void setExternalJobId(String externalJobId) {
        this.externalJobId = externalJobId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public LocalDateTime getFetchedAt() {
        return fetchedAt;
    }

    public void setFetchedAt(LocalDateTime fetchedAt) {
        this.fetchedAt = fetchedAt;
    }

    public JobPreference getJobPreference() {
        return jobPreference;
    }

    public void setJobPreference(JobPreference jobPreference) {
        this.jobPreference = jobPreference;
    }
}