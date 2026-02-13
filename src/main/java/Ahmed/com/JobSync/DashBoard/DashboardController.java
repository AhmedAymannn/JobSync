package Ahmed.com.JobSync.DashBoard;


import Ahmed.com.JobSync.JobPreference.JobPreferenceRepository;
import Ahmed.com.JobSync.resumes.ResumeRepository;
import Ahmed.com.JobSync.user.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Dashboard/")
public class DashboardController {
   private final DashboardService dashboardService ;
   private DashboardDto dashboardDto ;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }
}
