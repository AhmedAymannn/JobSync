package Ahmed.com.JobSync.Sync;
import Ahmed.com.JobSync.Job.Job;
import Ahmed.com.JobSync.Job.JobService;
import Ahmed.com.JobSync.JobPreference.JobPreference;
import Ahmed.com.JobSync.JobPreference.JobPreferenceService;
import Ahmed.com.JobSync.Sync.dtos.JSearchResponseDto;
import Ahmed.com.JobSync.Sync.dtos.JobQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class JobSyncService {
   private final JSearchClient jSearchClient ;
   private final JobPreferenceService jobPreferenceService ;
   private final JobMapper jobMapper;      // Added
   private final JobService jobService;

   public JobSyncService(JSearchClient jSearchClient ,JobPreferenceService jobPreferenceService ,JobMapper jobMapper,JobService jobService) {
      this.jSearchClient = jSearchClient;
      this.jobPreferenceService = jobPreferenceService;
      this.jobMapper = jobMapper;
      this.jobService = jobService;
   }
private static final Logger log = LoggerFactory.getLogger(JobSyncService.class);

   @Transactional
   public void syncJobs(long userId){
      JobPreference pref = jobPreferenceService.getUserPrefByUserId(userId);
      if (pref == null) {
         log.warn("No job preference found for user: {}", userId);
         return;
      }
      JobQuery query = new JobQuery();
      query.setTitle(pref.getJob_title());
      query.setLocation(pref.getLocation());
      query.setIsRemote(pref.getIsRemote());
      query.setPreferenceId(pref.getId());

      JSearchResponseDto rawJobs = jSearchClient.fetchRawJobs(query);
      List<Job> allFetchedJobs = jobMapper.toEntities(rawJobs.getData(), pref);
      List<Job> newJobs = allFetchedJobs.stream()
              .filter(job -> !jobService.existsByExternalJobId(job.getExternalJobId()))
              .toList();
      if (!newJobs.isEmpty()) {
         jobService.saveAll(newJobs);
      }
   }

}
