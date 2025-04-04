package com.example.medavieapp.model;

public class Fan {
    private FanSpeed speed;
    private String name;

    public Fan(String name) {
        this.name = name;
        this.speed = FanSpeed.OFF;
    }

    public FanSpeed getSpeed() {
        return speed;
    }

    public void setSpeed(FanSpeed speed) {
        this.speed = speed;
    }

    public void setSpeed(int speedValue) {
        this.speed = FanSpeed.fromValue(speedValue);
    }

    public boolean isOn() {
        return speed != FanSpeed.OFF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
} 