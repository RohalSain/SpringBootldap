package com.git.ldap.ldap.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@RequestMapping("/dashboard")
public class controllerDashboard {
    
    @GetMapping
    public String getHomePage() {
        return "dashboard";
    }
}