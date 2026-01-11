package Ahmed.com.JobSync.user;

import Ahmed.com.JobSync.DTOs.responseDtos.UserSummayDto;
import Ahmed.com.JobSync.Mapper.ResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService ;
    }

    @PostMapping("/createUser")
    public ResponseEntity <ResponseMapper> createUser(@RequestBody User user){
        UserSummayDto userSummayDto =  userService.createUser(user);
        ResponseMapper response = new ResponseMapper();

    }
    // get me
    @GetMapping("/getMe/{id}")
    public User getUser (@PathVariable Long id){
       return userService.getMe(id);
    }
    //updateMe
    @PutMapping("/updateMe")
    public void updateUser(){

    }
    // delete me
    @DeleteMapping("user/deleteMe")
    public void DeleteUser (){
    }

}
