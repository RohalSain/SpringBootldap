package com.git.ldap.ldap.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class controllerDashboard {
    
    @GetMapping
    public String getHomePage() {
        return "dashboard";
    }

}