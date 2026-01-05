package Ahmed.com.JobSync.user;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {

    // get me
    @GetMapping("user/getMe")
    public User getUser (){


        return new User() ;
    }
    //updateMe
    @PutMapping("user/updateMe")
    public void updateUser(){

    }
    // delete me
    @DeleteMapping("user/deleteMe")
    public void DeleteUser (){
    }

}
