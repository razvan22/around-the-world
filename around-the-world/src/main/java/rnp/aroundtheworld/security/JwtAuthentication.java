package rnp.aroundtheworld.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rnp.aroundtheworld.entities.LoginModel;
import rnp.aroundtheworld.entities.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthentication extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;


    public JwtAuthentication(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //Grab credentials and map them to LoginModel
        LoginModel credentials = null;

        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);
        } catch (IOException e){
            e.printStackTrace();
        }

        //Create a authentication token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUserName(),
                credentials.getPassword(),
                new ArrayList<>()
        );
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        return auth;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //Grab user entity
        User user = (User) authResult.getPrincipal();

        //Create a JWT token

        String token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME ))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));

        //Add token in respone
        response.addHeader(JwtProperties.HEADER_STRING,JwtProperties.TOKEN_PREFIX + token);

    }
}