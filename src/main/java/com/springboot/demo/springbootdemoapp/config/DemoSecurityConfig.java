package com.springboot.demo.springbootdemoapp.config;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
            .username("john")
            .password("{noop}test123")
            .roles("EMPLOYEE")
            .build();

        UserDetails mary = User.builder()
            .username("mary")
            .password("{noop}test123")
            .roles("EMPLOYEE", "MANAGER")
            .build();

        UserDetails susan = User.builder()
            .username("susam")
            .password("{noop}test123")
            .roles("EMPLOYEE", "MANAGER", "ADMIN")
            .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    // Restricting access based on roles
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
            configurer
                .requestMatchers(HttpMethod.GET, "/students").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/students/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/students").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/students/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/students/**").hasRole("ADMIN")
        );

        // use HTTP Basic authentication
        http.httpBasic();

        // disable CSRF
        http.csrf().disable();

        return http.build();
    }

}
