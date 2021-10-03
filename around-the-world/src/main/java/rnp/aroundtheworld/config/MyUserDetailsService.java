package rnp.aroundtheworld.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rnp.aroundtheworld.entities.User;
import rnp.aroundtheworld.security.UserDetailsServiceClass;
import rnp.aroundtheworld.services.UserService;

@Service
public class MyUserDetailsService extends UserDetailsServiceClass {
    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public BCryptPasswordEncoder getEncoder(){return encoder;}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("User with email :"+email+" was not found.");
        }
        return toUserDetails(user);
    }

    private UserDetails toUserDetails(User user){
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("USER").build();
    }
}
