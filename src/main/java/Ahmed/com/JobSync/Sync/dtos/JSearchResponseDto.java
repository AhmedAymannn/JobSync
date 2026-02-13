package Ahmed.com.JobSync.Sync.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JSearchResponseDto {
    private String status;
    private List<JobDto> data;


    public JSearchResponseDto() {
    }
    public JSearchResponseDto(String status, List<JobDto> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<JobDto> getData() {
        return data;
    }

    public void setData(List<JobDto> data) {
        this.data = data;
    }
}
