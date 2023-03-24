package com.springboot.demo.springbootdemoapp.coach;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley!!!!";
    }
}
