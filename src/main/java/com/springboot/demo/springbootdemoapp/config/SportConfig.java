package com.springboot.demo.springbootdemoapp.config;

import com.springboot.demo.springbootdemoapp.coach.Coach;
import com.springboot.demo.springbootdemoapp.coach.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
