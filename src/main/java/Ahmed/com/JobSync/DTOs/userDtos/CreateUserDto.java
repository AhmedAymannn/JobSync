package Ahmed.com.JobSync.DTOs.userDtos;

import jakarta.annotation.Nullable;

public class CreateUserDto {
    @Nullable
    String name ;
    @Nullable
    String password;
    @Nullable
    String email ;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
