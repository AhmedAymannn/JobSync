//package Ahmed.com.JobSync.common.exception;
//
//import Ahmed.com.JobSync.common.dto.ErrorResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotati000000000on.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    // Catch the specific email error
//    @ExceptionHandler(UserAlreadyExistsException.class)
//    public ResponseEntity<ErrorResponse> handleUserExists(UserAlreadyExistsException ex) {
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(new ErrorResponse(ex.getMessage(), 400));
//    }
//    // Best Practice: Catch everything else so the user never gets a 403/500 without a message
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(new ErrorResponse("Something went wrong on our side", 500));
//    }
//}
