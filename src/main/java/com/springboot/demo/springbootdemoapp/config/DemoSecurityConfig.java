package com.springboot.demo.springbootdemoapp.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    // add support for JDBC
    // table: users, authorities
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // query users
        jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, is_active from user where username=?");

        // query roles
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username, role from user where username=?");

        return jdbcUserDetailsManager;
    }

    // Restricting access based on roles
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
            configurer
                .requestMatchers(HttpMethod.GET, "/students").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/students/**").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/students").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/students/**").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/students/**").hasRole("ADMIN")
        );

        // use HTTP Basic authentication
        http.httpBasic();

        // disable CSRF
        http.csrf().disable();

        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails john = User.builder()
//            .username("john")
//            .password("{noop}test123")
//            .roles("EMPLOYEE")
//            .build();
//
//        UserDetails mary = User.builder()
//            .username("mary")
//            .password("{noop}test123")
//            .roles("EMPLOYEE", "MANAGER")
//            .build();
//
//        UserDetails susan = User.builder()
//            .username("susam")
//            .password("{noop}test123")
//            .roles("EMPLOYEE", "MANAGER", "ADMIN")
//            .build();
//
//        return new InMemoryUserDetailsManager(john, mary, susan);
//    }

}
