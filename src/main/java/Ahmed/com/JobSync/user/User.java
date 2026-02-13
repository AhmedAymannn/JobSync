package Ahmed.com.JobSync.user;
import Ahmed.com.JobSync.common.Enums.ExperienceLevel;
import Ahmed.com.JobSync.common.Enums.Role;
import Ahmed.com.JobSync.JobPreference.JobPreference;
import Ahmed.com.JobSync.jobApplication.Application;
import Ahmed.com.JobSync.resumes.Resume;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")

public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false , unique = true)
  private String email;
  @Column(nullable = false)
  private String password;
  @Column(nullable = false)
  private String name;
  @Enumerated(EnumType.STRING)
  private Role role ;
  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ExperienceLevel experienceLevel;
  private LocalDateTime createdAt = LocalDateTime.now();

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private JobPreference jobPreference;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "resume_id", referencedColumnName = "id") // Creates FK 'resume_id' in USER table
  private Resume resume;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Application> applications = new ArrayList<>();

  public User() {
  }

  public User(Long id, String email, String password, String name,Role role ,
              ExperienceLevel experienceLevel, LocalDateTime createdAt,
              JobPreference jobPreference, Resume resume, List<Application> applications) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.experienceLevel = experienceLevel;
    this.createdAt = createdAt;
    this.jobPreference = jobPreference;
    this.resume = resume;
    this.applications = applications;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ExperienceLevel getExperienceLevel() {
    return experienceLevel;
  }

  public void setExperienceLevel(ExperienceLevel experienceLevel) {
    this.experienceLevel = experienceLevel;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public JobPreference getJobPreference() {
    return jobPreference;
  }

  public void setJobPreference(JobPreference jobPreference) {
    this.jobPreference = jobPreference;
  }

  public Resume getResume() {
    return resume;
  }

  public void setResume(Resume resume) {
    this.resume = resume;
  }

  public List<Application> getApplications() {
    return applications;
  }

  public void setApplications(List<Application> applications) {
    this.applications = applications;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}