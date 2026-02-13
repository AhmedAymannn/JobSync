package Ahmed.com.JobSync.Sync;

import Ahmed.com.JobSync.Job.Job;
import Ahmed.com.JobSync.JobPreference.JobPreference;
import Ahmed.com.JobSync.Sync.dtos.JobDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// String Json <JSearchResponseDto> ==> Job Entity Object
@Component
public class JobMapper {
    public List <Job> toEntities (List <JobDto> jobDtos, JobPreference preference){
        // logic
        // loop for each JobDto and use the ToEntity , then return the list of jobs as List of Entities
        if (jobDtos == null || jobDtos.isEmpty()) {
            return new ArrayList<>();
        }
        List <Job> jobs = new ArrayList<>();
        for (JobDto jobDto : jobDtos) {
            Job jobEntity = ToEntity(jobDto, preference);
            jobs.add(jobEntity);
        }
        return jobs;
    }
    private Job ToEntity(JobDto dto, JobPreference preference) {
        if (dto == null) return null;
        Job job = new Job();
        job.setSource("JSearch");
        job.setExternalJobId(dto.getJobId());
        job.setTitle(dto.getJobTitle());
        job.setCompanyName(dto.getEmployerName());
        String location = (dto.getCity() != null ? dto.getCity() : "Remote")
                + (dto.getCountry() != null ? ", " + dto.getCountry() : "");
        job.setLocation(location);
        job.setJobUrl(dto.getApplyLink());
        job.setDescription(dto.getJobDescription());
        job.setPostedAt(dto.getPostedAt());
        job.setEmploymentType(dto.getEmploymentType());
        job.setJobPreference(preference);
        return job;
    }
}
