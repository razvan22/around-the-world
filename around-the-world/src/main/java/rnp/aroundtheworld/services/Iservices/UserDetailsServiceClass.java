package rnp.aroundtheworld.services.Iservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rnp.aroundtheworld.entities.User;
import rnp.aroundtheworld.repositories.UserRepository;
import rnp.aroundtheworld.security.MyUserDetails;

import java.util.List;


@Service
public class UserDetailsServiceClass implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByUsername(s);
        MyUserDetails myUserDetails = new MyUserDetails(user);

        return myUserDetails;
    }


    public User findByUsername(String email) {
        return userRepository.findByEmail(email);
    }


    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }


}

