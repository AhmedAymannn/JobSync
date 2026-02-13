package Ahmed.com.JobSync.common.exception;

public class JwtCreationException extends RuntimeException{
    public JwtCreationException(String message , Throwable cause){
        super(message , cause);
    }
}
