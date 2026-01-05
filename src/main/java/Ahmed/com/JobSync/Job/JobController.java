package Ahmed.com.JobSync.Job;

import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class JobController {
    // get all of jobs
    public List<Job> getJobs (){
        List <Job> jobs = new ArrayList();
        return jobs ;
    }
}
