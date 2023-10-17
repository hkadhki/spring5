package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    private SystemProfile profile = new DevProfile();

    public ProfileController(SystemProfile profile) {
        this.profile = profile;
    }

    @GetMapping("/")
    public String getProfile() {
        return profile.getProfile();
    }
}