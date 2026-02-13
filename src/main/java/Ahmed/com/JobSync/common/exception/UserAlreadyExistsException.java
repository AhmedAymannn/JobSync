package Ahmed.com.JobSync.common.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String emailAlreadyRegistered) {
        super(emailAlreadyRegistered);
    }
}
