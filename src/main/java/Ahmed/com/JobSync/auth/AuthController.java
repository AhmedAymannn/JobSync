package Ahmed.com.JobSync.auth;
import Ahmed.com.JobSync.auth.dtos.AuthResponseDto;
import Ahmed.com.JobSync.auth.dtos.LoginRequestDto;
import Ahmed.com.JobSync.auth.dtos.SignupRequestDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService ;
    public AuthController( AuthService authService) {
        this.authService = authService ;
    }
    //signUp
    @PostMapping("/signUp")
    public ResponseEntity <AuthResponseDto> signUp (@RequestBody SignupRequestDto requestDto ){
        AuthResponseDto createdUser = authService.signUp(requestDto);
        return ResponseEntity.ok(createdUser);
    }
    @GetMapping("/me")
    private void getMe(){
    }
    @PostMapping("/login")
    public ResponseEntity <AuthResponseDto> login (@RequestBody LoginRequestDto requestDto , @RequestHeader HttpHeaders header){
        AuthResponseDto loggedInUser =  authService.login(requestDto,header);
        return ResponseEntity.ok(loggedInUser);
    }
}
