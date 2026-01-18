package Ahmed.com.JobSync.DTOs.authDtos;

public class LoginRequestDto {
    private String name ;
    private String password ;

    public LoginRequestDto() {

    }
    public LoginRequestDto(String password, String name) {
        this.password = password;
        this.name = name;
    }

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
}
