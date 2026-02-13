package Ahmed.com.JobSync.auth;
import Ahmed.com.JobSync.auth.dtos.AuthResponseDto;
import Ahmed.com.JobSync.auth.dtos.LoginRequestDto;
import Ahmed.com.JobSync.auth.dtos.SignupRequestDto;
import Ahmed.com.JobSync.common.Enums.Role;
import Ahmed.com.JobSync.common.exception.UserAlreadyExistsException;
import Ahmed.com.JobSync.security.JwtService;
import Ahmed.com.JobSync.user.User;
import Ahmed.com.JobSync.user.UserRepository;
import Ahmed.com.JobSync.user.dtos.UserResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService ;
    // Best Practice: Use Constructor Injection instead of @Autowired on fields
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder , JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService ;
    }
    @Transactional
    public AuthResponseDto signUp (SignupRequestDto requestDto) {
        // 1. Logic: Check if user exists
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered");
        }
        // 2 : hash the password
        String hashedPassword = passwordEncoder.encode(requestDto.getPassword());
        // create the user entity
        User user = new User();
        // requestDto => entity
        user.setEmail(requestDto.getEmail());
        user.setPassword(hashedPassword);
        user.setName(requestDto.getName());
        user.setRole(Role.USER);
        // saving into DB
        User savedUser = userRepository.save(user) ;
        // Create the JWT
        String token = jwtService.createToken(savedUser.getId(), savedUser.getRole());
        // Entity ==> ResponseDto
        // 6. Map to a combined Response DTO
        UserResponseDto userProfile = new UserResponseDto();
        userProfile.setId(savedUser.getId());
        userProfile.setEmail(savedUser.getEmail());
        userProfile.setName(savedUser.getName());
        userProfile.setCreatedAt(savedUser.getCreatedAt());
        userProfile.setExperienceLevel(savedUser.getExperienceLevel());
        return new AuthResponseDto(token,userProfile);
    }

    public AuthResponseDto login (LoginRequestDto requestDto , HttpHeaders header){
        // if user exist
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(()-> new RuntimeException("Wrong Email Or Password "));

        // if password matched
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Email or Password");
        }

        // generate token
       String token = jwtService.createToken(user.getId() , user.getRole());
        UserResponseDto userProfile = new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getExperienceLevel()
        );
        return new AuthResponseDto(token,userProfile);
    }
}
