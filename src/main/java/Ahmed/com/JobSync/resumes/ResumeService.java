package Ahmed.com.JobSync.resumes;
import Ahmed.com.JobSync.utils.Storage.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import Ahmed.com.JobSync.user.* ;
@Service
public class ResumeService {
    private final UserRepository userRepository ;
    private final ResumeRepository resumeRepository;
    private final FileStorageUtil fileStorageUtil ;
    private final FileValidationUtil fileValidationUtil ;

    public ResumeService(
            UserRepository userRepository,
            ResumeRepository resumeRepository,
            FileStorageUtil fileStorageUtil,
            FileValidationUtil fileValidationUtil ) {
        this.userRepository = userRepository;
        this.resumeRepository = resumeRepository;
        this.fileStorageUtil = fileStorageUtil;
        this.fileValidationUtil = fileValidationUtil;
    }

    public void uploadResume (MultipartFile file , long userId){
        // validate the
        // 1 : validation about the file (call the utility of validation

        // 2 : call createUserDir
            String absolutePath = fileStorageUtil.storeFile(file,userId);
            System.out.println("file stored in ===> "+absolutePath);
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
            // 3 : save url in db
            Resume resume = resumeRepository.findByUserId(userId).orElse(new Resume());
            resume.setUser(user);
            resume.setFileSize(file.getSize());
            resume.setFileUrl(absolutePath);
            resume.setFileName(file.getOriginalFilename());
            resume.setUploadedAt(LocalDateTime.now());
            resumeRepository.save(resume);
    }
    public void updateResume (long userId , MultipartFile file){

    }

    public void deleteResume (long userId){

    }
}
