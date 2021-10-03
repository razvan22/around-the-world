package rnp.aroundtheworld.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/v1/user/all").hasRole("ADMIN")
                .antMatchers("/api/v1/**").authenticated()
                .and()
                .httpBasic();
    }
}



//                .antMatchers("/index.html").permitAll()
//                        .antMatchers("/profile/**").authenticated()
//                        .antMatchers("/admin/**").hasRole("ADMIN")
//                        .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
//                        .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
//                        .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
//                        .antMatchers("/api/public/users").hasRole("ADMIN")

