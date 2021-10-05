package rnp.aroundtheworld.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import rnp.aroundtheworld.services.Iservices.UserDetailsServiceClass;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsServiceClass userDetailsServiceClass;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/user/new").permitAll()
                .antMatchers("/api/v1/user/all").hasRole("ADMIN")
                .antMatchers("/api/v1/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login");
    }


    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceClass);
        return daoAuthenticationProvider;

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}



//                .antMatchers("/index.html").permitAll()
//                        .antMatchers("/profile/**").authenticated()
//                        .antMatchers("/admin/**").hasRole("ADMIN")
//                        .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
//                        .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
//                        .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
//                        .antMatchers("/api/public/users").hasRole("ADMIN")

