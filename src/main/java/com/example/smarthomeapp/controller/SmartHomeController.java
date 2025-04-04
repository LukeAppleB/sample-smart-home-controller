package com.example.smarthomeapp.controller;

import com.example.smarthomeapp.model.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;

@RestController
@RequestMapping("/api")
@EnableScheduling
public class SmartHomeController {
    //in a real situation this information would be stored in a DB tied to the user
    //these apis would update the objects from their current state to the new one,
    //    notify the device to the update
    //    and save the new state in the DB
    private final Fan fan = new Fan("Living Room Fan");
    private final Light light = new Light("Living Room Light");
    private final AirConditioner ac = new AirConditioner("Living Room AC");

    @GetMapping("/status")
    public String getStatus() {
        return "Smart Home System is Online";
    }

    @GetMapping("/devices")
    public String getDevices() {
        return String.format("Connected Devices:\n" +
                "1. %s (Status: %s)\n" +
                "2. %s (Status: %s)\n" +
                "3. %s (Status: %s, Temperature: %d°C)",
                fan.getName(), fan.isOn() ? "ON" : "OFF",
                light.getName(), light.isOn() ? "ON" : "OFF",
                ac.getName(), ac.isOn() ? "ON" : "OFF", ac.getTemperature());
    }

    @PostMapping("/fan/speed")
    public String setFanSpeed(@RequestParam int speed) {
        fan.setSpeed(speed);
        return String.format("Fan speed set to %d", speed);
    }

    @GetMapping("/fan/status")
    public String getFanStatus() {
        return String.format("Fan is %s at speed %d", 
            fan.isOn() ? "ON" : "OFF", 
            fan.getSpeed().getValue());
    }

    @PostMapping("/light/toggle")
    public String toggleLight() {
        light.setOn(!light.isOn());
        return String.format("Light is now %s", light.isOn() ? "ON" : "OFF");
    }

    @GetMapping("/light/status")
    public String getLightStatus() {
        return String.format("Light is %s", light.isOn() ? "ON" : "OFF");
    }

    @PostMapping("/ac/temperature")
    public String setACTemperature(@RequestParam int temperature) {
        ac.setTemperature(temperature);
        return String.format("AC temperature set to %d°C", temperature);
    }

    @PostMapping("/ac/toggle")
    public String toggleAC() {
        if (ac.isOn()) {
            ac.turnOff();
        } else {
            ac.turnOn();
        }
        return String.format("AC is now %s", ac.isOn() ? "ON" : "OFF");
    }

    @GetMapping("/ac/status")
    public String getACStatus() {
        return String.format("AC is %s at %d°C", 
            ac.isOn() ? "ON" : "OFF", 
            ac.getTemperature());
    }

    @Scheduled(cron = "0 0 1 1 1 *")
    public void performAnnualUpdate() {
        fan.setSpeed(FanSpeed.OFF);
        light.setOn(false);
        ac.turnOff();
    }
} 