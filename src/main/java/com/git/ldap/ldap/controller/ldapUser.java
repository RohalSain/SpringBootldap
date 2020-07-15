package com.git.ldap.ldap.controller;

import javax.validation.Valid;

import com.git.ldap.ldap.ldapCrudOperations.ldapCrudImple;
import com.git.ldap.ldap.secuity.UserRepository;
import com.git.ldap.ldap.secuity.model.LdapUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/userCreate")
public class ldapUser {
    
    @Autowired
    ldapCrudImple ldap;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping
    public String createldapUser(Model model) {
        model.addAttribute("userCreation", new LdapUser());
        return "user";
    }

    @PostMapping
    public String postlogin(@Valid LdapUser user ,BindingResult errors, Model model) {
        
        String password = user.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        boolean status =  ldap.create(user);
        //  if(status) {
        //      user.setId(user.UniqueIdGeneration());
        //      userRepository.save(user);
        //  } else {
        //     //  error display with Save page
        //  }
        return "redirect:/login";
}
    
}