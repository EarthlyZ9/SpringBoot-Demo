package com.springboot.demo.springbootdemoapp.coach;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Spend 30 mins in batting practice!!!";
    }
}
