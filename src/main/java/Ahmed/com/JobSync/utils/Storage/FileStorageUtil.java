package Ahmed.com.JobSync.utils.Storage;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


@Component
public class FileStorageUtil {
    final private String USER_DIR_PREFIX = "user_";
    final private String baseStoragePath = "D:\\javaProjects\\JobSync\\storage";
    private long maxFileSize ;
    // generate folder for user

    public String storeFile ( MultipartFile file , Long userId) {
        // 1 : create relativePath
        try {
            // Define the folder and the file name
            Path userPath = Paths.get(baseStoragePath ).resolve( USER_DIR_PREFIX + userId);
            // Create the folder if it's not there
            Files.createDirectories(userPath);
            Path targetFile = userPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
            // Return the path as a String so the DB can store it
            return targetFile.toString();
        }catch (IOException e){
            throw new RuntimeException("Disk I/O Error: Could not store file", e);
        }

    }
    public void generateUserResumePath (Long userId){
        //Builds standardized file path
    }
    public void deleteFile (){
    }
    public void deleteUserDirectory (){
    }
    public void ensureDirectoryExists (){
    }



}
