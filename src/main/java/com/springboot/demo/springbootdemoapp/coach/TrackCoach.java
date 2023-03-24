package com.springboot.demo.springbootdemoapp.coach;

import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        return "Run an hard 5K!!!";
    }
}
