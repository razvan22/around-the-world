package rnp.aroundtheworld.filters;

import org.springframework.web.filter.OncePerRequestFilter;
import rnp.aroundtheworld.security.JwtProperties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthorizationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/v1/user/")){
            String header = request.getHeader(JwtProperties.HEADER_STRING);
            System.out.println("REQUEST HEADER :" + header);
            filterChain.doFilter(request, response);

        } else {
            filterChain.doFilter(request, response);
        }
    }


}
