package Ahmed.com.JobSync.common.utils.Storage;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileValidationUtil {
    // validate file size , file ext , no duplicates files per user

    public boolean validationFile (MultipartFile file){
        return true ;
    }
}
