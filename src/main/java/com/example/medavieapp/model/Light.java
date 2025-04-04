package com.example.medavieapp.model;

public class Light {
    private boolean isOn;
    private String name;

    public Light(String name) {
        this.name = name;
        this.isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
} 