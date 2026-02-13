package Ahmed.com.JobSync.jobApplication;
import Ahmed.com.JobSync.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {
    public Application(Long id, User user, Long jobId, Long resumeId, String status, LocalDateTime appliedAt, String notes) {
        this.id = id;
        this.user = user;
        this.jobId = jobId;
        this.resumeId = resumeId;
        this.status = status;
        this.appliedAt = appliedAt;
        this.notes = notes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // This creates the 'user_id' column in the DB
    private User user;  // FK to User

    private Long jobId;   // FK to Job

    private Long resumeId; // FK to Resume

    private String status;  // pending | applied | failed | duplicated

    private LocalDateTime appliedAt;

    private String notes;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
