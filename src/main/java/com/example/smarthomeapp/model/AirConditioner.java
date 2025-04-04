package com.example.smarthomeapp.model;

public class AirConditioner {
    private boolean isOn;
    private int temperature;
    private String name;
    private static final int MIN_TEMPERATURE = 16;
    private static final int MAX_TEMPERATURE = 30;

    public AirConditioner(String name) {
        this.name = name;
        this.isOn = false;
        this.temperature = 22;
    }

    public boolean isOn() {
        return isOn;
    }

    public void turnOn() {
        this.isOn = true;
    }

    public void turnOff() {
        this.isOn = false;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        if (temperature < MIN_TEMPERATURE || temperature > MAX_TEMPERATURE) {
            throw new IllegalArgumentException("Temperature must be between " + MIN_TEMPERATURE + " and " + MAX_TEMPERATURE);
        }
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
} 