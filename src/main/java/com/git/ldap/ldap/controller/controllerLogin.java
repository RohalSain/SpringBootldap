package com.git.ldap.ldap.controller;

import javax.validation.Valid;

import com.git.ldap.ldap.secuity.model.Login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controllerLogin {
    
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login",new Login());
        model.addAttribute("status","false");
        return "login";
    }

    @PostMapping("/login")
    public String postlogin(@Valid Login login,BindingResult errors, Model model) {
        if(errors.hasErrors() ){
            model.addAttribute("status","true"); 
            model.addAttribute("error", "Username/Password error");
            return "login";
        }
        return "redirect:/dashboard";
    }
    @GetMapping("/logout")
    public String getLogOut() {
        return "logout";
    }
}