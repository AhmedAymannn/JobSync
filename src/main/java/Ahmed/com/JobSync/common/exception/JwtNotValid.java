package Ahmed.com.JobSync.common.exception;

public class JwtNotValid extends RuntimeException{
    public JwtNotValid(String message, Throwable cause) {
        super(message, cause);
    }
}
