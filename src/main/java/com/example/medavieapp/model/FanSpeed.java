package com.example.medavieapp.model;

public enum FanSpeed {
    OFF(0),
    LOW(1),
    HIGH(2);

    private final int value;

    FanSpeed(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FanSpeed fromValue(int value) {
        for (FanSpeed speed : FanSpeed.values()) {
            if (speed.getValue() == value) {
                return speed;
            }
        }
        throw new IllegalArgumentException("Invalid fan speed value: " + value);
    }
} 