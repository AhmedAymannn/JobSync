package Ahmed.com.JobSync.Job;

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
    @Column(nullable = false)// "LinkedIn"
    private String externalJobId;
    @Column(nullable = false) // LinkedIn job ID
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String jobUrl;
    @Column(nullable = false)
    private LocalDateTime postedAt;
    private LocalDateTime fetchedAt = LocalDateTime.now();

    public Job() {
    }

    public Job(Long id, String source, String externalJobId, String title, String companyName, String jobUrl, String location, LocalDateTime postedAt, LocalDateTime fetchedAt) {
        this.id = id;
        this.source = source;
        this.externalJobId = externalJobId;
        this.title = title;
        this.companyName = companyName;
        this.jobUrl = jobUrl;
        this.location = location;
        this.postedAt = postedAt;
        this.fetchedAt = fetchedAt;
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
}