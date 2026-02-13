package Ahmed.com.JobSync.Job;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository ;
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    public boolean existsByExternalJobId(String externalJobId) {
        return jobRepository.existsByExternalJobId(externalJobId);
    }
    public void saveAll(List<Job> jobs) {
        jobRepository.saveAll(jobs);
    }
}
