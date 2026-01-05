package Ahmed.com.JobSync.resumes;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResumesController {
    @PostMapping("resumes/upLoadResume")
    public void uploadResume (){

    }
    @GetMapping("resumes/myResume")
    public void getMyResume (){

    }




}
