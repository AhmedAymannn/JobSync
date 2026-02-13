package Ahmed.com.JobSync.common.dto;

public class ErrorResponse {
    private String message;
    private int status;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    // You MUST have these getters so Spring can turn it into JSON
    public String getMessage() { return message; }
    public int getStatus() { return status; }
}
