package Ahmed.com.JobSync.DashBoard;


import Ahmed.com.JobSync.JobPreference.JobPreferenceRepository;
import Ahmed.com.JobSync.resumes.ResumeRepository;
import Ahmed.com.JobSync.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    private final UserRepository userRepository ;
    private final ResumeRepository resumeRepository ;
    private final JobPreferenceRepository jobPreferenceRepository ;

    public DashboardService(UserRepository userRepository, ResumeRepository resumeRepository, JobPreferenceRepository jobPreferenceRepository) {
        this.userRepository = userRepository;
        this.resumeRepository = resumeRepository;
        this.jobPreferenceRepository = jobPreferenceRepository;
    }

    //getUserDashboard(userId)
        //What it does: Responds to the frontend request for the main view.
        //Returns: A full DashboardDTO.
   // calculateApplicationStats(userId)
}

