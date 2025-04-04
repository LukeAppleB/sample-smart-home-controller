package com.example.smarthomeapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SmartHomeController.class)
public class SmartHomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getStatus_shouldReturnOnline() throws Exception {
        mockMvc.perform(get("/api/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("Smart Home System is Online"));
    }

    @Test
    public void getDevices_shouldReturnAllDevices() throws Exception {
        mockMvc.perform(get("/api/devices"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Living Room Fan")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Living Room Light")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Living Room AC")));
    }

    @Test
    public void fanControls_shouldWorkCorrectly() throws Exception {
        mockMvc.perform(get("/api/fan/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("Fan is OFF at speed 0"));

        mockMvc.perform(post("/api/fan/speed?speed=1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Fan speed set to 1"));

        mockMvc.perform(get("/api/fan/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("Fan is ON at speed 1"));

        mockMvc.perform(post("/api/fan/speed?speed=0"))
                .andExpect(status().isOk())
                .andExpect(content().string("Fan speed set to 0"));

        mockMvc.perform(get("/api/fan/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("Fan is OFF at speed 0"));
    }

    @Test
    public void lightControls_shouldWorkCorrectly() throws Exception {
        mockMvc.perform(get("/api/light/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("Light is OFF"));

        mockMvc.perform(post("/api/light/toggle"))
                .andExpect(status().isOk())
                .andExpect(content().string("Light is now ON"));

        mockMvc.perform(get("/api/light/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("Light is ON"));

        mockMvc.perform(post("/api/light/toggle"))
                .andExpect(status().isOk())
                .andExpect(content().string("Light is now OFF"));

        mockMvc.perform(get("/api/light/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("Light is OFF"));
    }

    @Test
    public void acControls_shouldWorkCorrectly() throws Exception {
        mockMvc.perform(get("/api/ac/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("AC is OFF at 22°C"));

        mockMvc.perform(post("/api/ac/toggle"))
                .andExpect(status().isOk())
                .andExpect(content().string("AC is now ON"));

        mockMvc.perform(post("/api/ac/temperature?temperature=24"))
                .andExpect(status().isOk())
                .andExpect(content().string("AC temperature set to 24°C"));

        mockMvc.perform(get("/api/ac/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("AC is ON at 24°C"));

        mockMvc.perform(post("/api/ac/toggle"))
                .andExpect(status().isOk())
                .andExpect(content().string("AC is now OFF"));

        mockMvc.perform(get("/api/ac/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("AC is OFF at 24°C"));
    }
} 