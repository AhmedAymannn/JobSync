package Ahmed.com.JobSync.user;

import Ahmed.com.JobSync.user.dtos.UserSummayDto;
import Ahmed.com.JobSync.Mapper.ResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService ;
    }

    // get me
    @GetMapping("/{id}")
    public User getUser (@PathVariable Long id){
       return userService.getUser(id);
    }
    //updateMe

    @PutMapping("/updateMe")
    public void updateUser(){

    }
    // delete me
    @DeleteMapping("user/deleteMe")
    public void DeleteUser (){
    }
    @GetMapping("test")
    public ResponseEntity <User> test (){
        Long UserId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       User user= userService.test(UserId);
       return ResponseEntity.ok(user);
    }

}
