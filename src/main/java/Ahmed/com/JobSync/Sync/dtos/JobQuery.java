package Ahmed.com.JobSync.Sync.dtos;
import lombok.*;

@Data
public class JobQuery {
    private String title;
    private String location;
    private Boolean isRemote;
    private Long preferenceId;

    public JobQuery() {
    }

    public JobQuery(String title, String location, Boolean isRemote, Long preferenceId) {
        this.title = title;
        this.location = location;
        this.isRemote = isRemote;
        this.preferenceId = preferenceId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsRemote() {
        return isRemote;
    }

    public void setIsRemote(Boolean isRemote) {
        this.isRemote =isRemote;
    }

    public Long getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(Long preferenceId) {
        this.preferenceId = preferenceId;
    }
    // Kept for logging/tracking
}