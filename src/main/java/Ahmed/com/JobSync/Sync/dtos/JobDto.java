package Ahmed.com.JobSync.Sync.dtos;

import Ahmed.com.JobSync.common.Enums.EmploymentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobDto {
    @JsonProperty("job_id")
    private String jobId;
    @JsonProperty("employer_name")
    private String employerName;

    @JsonProperty("job_title")
    private String jobTitle;

    @JsonProperty("job_description")
    private String jobDescription;

    @JsonProperty("job_apply_link")
    private String applyLink;

    @JsonProperty("job_city")
    private String city;

    @JsonProperty("job_country")
    private String country;

    @JsonProperty("job_posted_at_datetime_utc")
    private LocalDateTime postedAt;

    @JsonProperty("job_employment_type")
    private EmploymentType employmentType;

    public JobDto() {
    }

    public JobDto(String jobId, String employerName, String jobTitle, String jobDescription, String applyLink, String city, String country,
                  LocalDateTime postedAt, EmploymentType employmentType) {
        this.jobId = jobId;
        this.employerName = employerName;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.applyLink = applyLink;
        this.city = city;
        this.country = country;
        this.postedAt = postedAt;
        this.employmentType = employmentType;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getApplyLink() {
        return applyLink;
    }

    public void setApplyLink(String applyLink) {
        this.applyLink = applyLink;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }
}