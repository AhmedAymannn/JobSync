package Ahmed.com.JobSync.user;

<<<<<<< Updated upstream
import Ahmed.com.JobSync.Enums.ExperienceLevel;
import Ahmed.com.JobSync.resumes.Resume;
import jakarta.persistence.*;
import java.time.LocalDateTime;
=======
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
                @Column(length = 20)
                private ExperienceLevel experienceLevel;
                private LocalDateTime createdAt = LocalDateTime.now();
                @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
                private JobPreference jobPreference;
                @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
                private Resume resume;
                @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
                private List<SearchHistory> searchHistory = new ArrayList<>();
                @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
                private List<Application> applications = new ArrayList<>();
>>>>>>> Stashed changes

@Entity
@Table(name = "users")
public class User {

<<<<<<< Updated upstream
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
=======
        public User() {
>>>>>>> Stashed changes

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Fixed: Added nullable = false to match your requirements
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ExperienceLevel experienceLevel;

    // Fixed: Added updatable = false so the timestamp doesn't change on updates
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToOne()
    @JoinColumn(name = "resume_id" ,referencedColumnName = "id")
    private Resume resume;

    // Default constructor (Required by JPA/Jackson)
    public User() {
    }
    // Overloaded constructor
    public User(Long id, String email, String password, String name, ExperienceLevel experienceLevel , Resume resume) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.experienceLevel = experienceLevel;
        this.createdAt = LocalDateTime.now();
        this.resume = resume;
    }

    // --- GETTERS AND SETTERS ---

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    // FIXED: Now correctly assigning the parameter to the class field
    public void setName(String name) {
        this.name = name;
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

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}