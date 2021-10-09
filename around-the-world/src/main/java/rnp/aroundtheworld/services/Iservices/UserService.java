package rnp.aroundtheworld.services.Iservices;

import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rnp.aroundtheworld.entities.User;
import rnp.aroundtheworld.repositories.UserRepository;
import rnp.aroundtheworld.security.JwtProperties;
import rnp.aroundtheworld.security.MyUserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;


@Service
public class UserService implements UserDetailsService {


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


    public ResponseEntity save(User user){
            if (!isEmailTaken(user.getEmail())){
                user.setPassword( new BCryptPasswordEncoder().encode(user.getPassword()));
                userRepository.save(user);
                return new ResponseEntity(HttpStatus.CREATED);
            }
            else return new ResponseEntity(HttpStatus.CONFLICT);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public boolean isUserAuthenticated(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX,"");

        String userName = JWT.require(HMAC512(JwtProperties.SECRET.getBytes()))
                .build()
                .verify(token)
                .getSubject();

        if (userName != null) {
            User user = findByUsername(userName);
            MyUserDetails principal = new MyUserDetails(user);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());

            return auth.isAuthenticated();
        }
        return false;
    }

    public String getUserNameFromJwtToken(HttpServletRequest request){
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX,"");

        return JWT.require(HMAC512(JwtProperties.SECRET.getBytes()))
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean isEmailTaken (String email){
        return  userRepository.findByEmail(email) != null;
    }

}

