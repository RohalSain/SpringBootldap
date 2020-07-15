package com.git.ldap.ldap.controller;

import javax.validation.Valid;

import com.git.ldap.ldap.ldapCrudOperations.ldapCrudImple;
import com.git.ldap.ldap.secuity.model.LdapUser;
import com.git.ldap.ldap.secuity.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class controlleResetPassword {
    
    @Autowired
    ldapCrudImple ldap;

    @GetMapping("/resetPassword")
    public String ResetPassword(Model m) {
        m.addAttribute("changePassword", new User());
        return "resetPassword";
    }

    @PostMapping("/resetPassword")
    public String postResetPassword(@Valid LdapUser user ,BindingResult errors, Model model) {
        if(errors.hasErrors()) {
            return "resetPassword";
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        ldap.update(user);
        return "redirect:/login";
    }
}