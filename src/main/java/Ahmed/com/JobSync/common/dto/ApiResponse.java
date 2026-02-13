package Ahmed.com.JobSync.common.dto;

public record ApiResponse <T> (
        boolean success,
        int statusCode,
        String message,
        T data,
        long timestamp
){
    public static <T> ApiResponse<T> success(T data, String message , int statusCode  ) {
        return new ApiResponse<>(true, 200, message, data, System.currentTimeMillis());
    }
}


