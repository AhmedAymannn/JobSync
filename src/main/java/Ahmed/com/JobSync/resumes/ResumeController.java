package Ahmed.com.JobSync.resumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("resumes/")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;


    @PostMapping("upLoadResume/{userId}")
    public void uploadResume (
            @RequestParam("file") MultipartFile UploadedFile ,
            @PathVariable Long userId){

        try {
            resumeService.uploadResume(UploadedFile , userId);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @GetMapping("myResume")
    public void getMyResume (){

    }




}
