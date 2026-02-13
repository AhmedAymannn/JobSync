package Ahmed.com.JobSync.JobPreference;

import org.springframework.stereotype.Service;

@Service
public class JobPreferenceService {
    private final JobPreferenceRepository jobPreferenceRepository ;

    public JobPreferenceService(JobPreferenceRepository jobPreferenceRepository) {
        this.jobPreferenceRepository = jobPreferenceRepository;
    }

    public JobPreference getUserPrefByUserId(long userId){
        return jobPreferenceRepository.getReferenceByUserId(userId);
    }
}
