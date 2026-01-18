package Ahmed.com.JobSync.security;

import Ahmed.com.JobSync.user.User;
import Ahmed.com.JobSync.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public final class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    UserRepository userRepository ;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("user with this email not exist"));
        return new MyUserDetails(user);

    }


}
