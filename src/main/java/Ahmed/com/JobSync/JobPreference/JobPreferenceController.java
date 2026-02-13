package Ahmed.com.JobSync.JobPreference;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("preferences")
public class JobPreferenceController {
    private final JobPreferenceService jobPreferenceService ;

    public JobPreferenceController(JobPreferenceService jobPreferenceService) {
        this.jobPreferenceService = jobPreferenceService;
    }

    @Autowired

    @PostMapping("/")
    public void addPreferences(){

    }

    @GetMapping("/")
    public void getPreferences (
            @PathVariable Long id ,
            @RequestBody JobPreference request){


    }

}
