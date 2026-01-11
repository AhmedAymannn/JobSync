package Ahmed.com.JobSync.user;

import Ahmed.com.JobSync.DTOs.responseDtos.UserSummayDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final private UserRepository userRepository ;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository ;
    }

    public UserSummayDto createUser (User user){
        User newUser = userRepository.save(user);
        UserSummayDto response = new UserSummayDto() ;
        response.setEmail(newUser.getEmail());
        response.setName(newUser.getName());
        return response ;

        }

    public User getMe (Long useeId){
        return userRepository.findById(useeId).orElseThrow();
    }

    public User updateUser (){
      return new User();

    }

}
