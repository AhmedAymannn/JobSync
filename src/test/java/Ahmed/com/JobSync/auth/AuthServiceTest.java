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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpHeaders;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    private SignupRequestDto signupRequestDto;
    private LoginRequestDto loginRequestDto;
    private User testUser;
    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        signupRequestDto = new SignupRequestDto();
        signupRequestDto.setName("Test User");
        signupRequestDto.setEmail("test@example.com");
        signupRequestDto.setPassword("password123");

        loginRequestDto = new LoginRequestDto("password123", "test@example.com");

        testUser = new User();
        testUser.setId(1L);
        testUser.setName("Test User");
        testUser.setEmail("test@example.com");
        testUser.setPassword("hashedPassword");
        testUser.setRole(Role.USER);
        testUser.setCreatedAt(LocalDateTime.now());

        headers = new HttpHeaders();
    }

    @Test
    void signUp_Successful() {
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        when(jwtService.createToken(anyLong(), any(Role.class))).thenReturn("jwt-token");

        AuthResponseDto response = authService.signUp(signupRequestDto);

        assertNotNull(response);
        assertEquals("jwt-token", response.getAccessToken());
        assertNotNull(response.getUser());
        assertEquals("Test User", response.getUser().getName());
        assertEquals("test@example.com", response.getUser().getEmail());

        verify(userRepository).existsByEmail("test@example.com");
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(User.class));
        verify(jwtService).createToken(1L, Role.USER);
    }

    @Test
    void signUp_UserAlreadyExists_ThrowsException() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> authService.signUp(signupRequestDto));

        verify(userRepository).existsByEmail("test@example.com");
        verify(userRepository, never()).save(any(User.class));
        verify(jwtService, never()).createToken(anyLong(), any(Role.class));
    }

    @Test
    void login_Successful() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtService.createToken(anyLong(), any(Role.class))).thenReturn("jwt-token");

        AuthResponseDto response = authService.login(loginRequestDto, headers);

        assertNotNull(response);
        assertEquals("jwt-token", response.getAccessToken());
        assertNotNull(response.getUser());
        assertEquals("Test User", response.getUser().getName());
        assertEquals("test@example.com", response.getUser().getEmail());

        verify(userRepository).findByEmail("test@example.com");
        verify(passwordEncoder).matches("password123", "hashedPassword");
        verify(jwtService).createToken(1L, Role.USER);
    }

    @Test
    void login_UserNotFound_ThrowsException() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authService.login(loginRequestDto, headers));

        verify(userRepository).findByEmail("test@example.com");
        verify(passwordEncoder, never()).matches(anyString(), anyString());
        verify(jwtService, never()).createToken(anyLong(), any(Role.class));
    }

    @Test
    void login_InvalidPassword_ThrowsException() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertThrows(RuntimeException.class, () -> authService.login(loginRequestDto, headers));

        verify(userRepository).findByEmail("test@example.com");
        verify(passwordEncoder).matches("password123", "hashedPassword");
        verify(jwtService, never()).createToken(anyLong(), any(Role.class));
    }

    @Test
    void signUp_RequestDtoConstructorOrder() {
        SignupRequestDto dto = new SignupRequestDto("John Doe", "john@example.com", "password");
        
        assertEquals("John Doe", dto.getName());
        assertEquals("john@example.com", dto.getEmail());
        assertEquals("password", dto.getPassword());
    }

    @Test
    void login_RequestDtoConstructorOrder() {
        LoginRequestDto dto = new LoginRequestDto("password", "email@example.com");
        
        assertEquals("email@example.com", dto.getEmail());
        assertEquals("password", dto.getPassword());
    }

    @Test
    void authResponseDto_ConstructorAndSetters() {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setName("Test User");
        userDto.setEmail("test@example.com");
        
        AuthResponseDto authResponse = new AuthResponseDto("token123", userDto);
        
        assertEquals("token123", authResponse.getAccessToken());
        assertEquals(userDto, authResponse.getUser());
        
        authResponse.setAccessToken("newToken");
        authResponse.setUser(null);
        
        assertEquals("newToken", authResponse.getAccessToken());
        assertNull(authResponse.getUser());
    }
}
