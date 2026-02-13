package Ahmed.com.JobSync.auth.dtos;

import Ahmed.com.JobSync.user.dtos.UserResponseDto;

public class AuthResponseDto {
    private String accessToken;
    private UserResponseDto user;

    public AuthResponseDto(String accessToken, UserResponseDto user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public UserResponseDto getUser() {
        return user;
    }
}