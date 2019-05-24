package com.sorsix.librarianapi.security;

import com.sorsix.librarianapi.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserRepository userRepository;
    private final AuthSuccessHandler successHandler;
    private final AuthFailureHandler failureHandler;

    public SecurityConfig(UserRepository userRepository, AuthSuccessHandler successHandler, AuthFailureHandler failureHandler) {
        this.userRepository = userRepository;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                    .and()
                .formLogin()
                    .loginProcessingUrl("/api/public/login")
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                    .and()
                .authorizeRequests()
                    .antMatchers("/api/leases", "/api/leases/update", "/api/leases/user").hasRole("ADMIN")
                    .antMatchers("/api/leases/new", "/api/leases/my").hasRole("USER")
                    .antMatchers("/api/logout").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/api/public/**").permitAll()
                    .anyRequest().fullyAuthenticated()
                    .and()
                .logout()
                    .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                    .passwordEncoder(passwordEncoder());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(this.userRepository);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
//    @Bean
//    public AuthenticationSuccessHandler successHandler() {
//        return new AuthSuccessHandler();
//    }
//
//    @Bean
//    public AuthenticationFailureHandler failureHandler() {
//        return new AuthFailureHandler();
//    }
//
//    @Bean
//    public LogoutSuccessHandler logoutSuccessHandler() {
//        return new LogoutSuccessHandler();
//    }
}
