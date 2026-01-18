package Ahmed.com.JobSync.JobPreference;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("preferences")
public class JobPreferenceController {
    JobPreferenceService jobPreferenceService ;
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
