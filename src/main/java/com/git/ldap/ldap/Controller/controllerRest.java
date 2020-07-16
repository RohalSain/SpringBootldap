package com.git.ldap.ldap.Controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.git.ldap.ldap.LdapSecurity.ldapCrudImple;
import com.git.ldap.ldap.Model.LdapUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class controllerRest {

    @Autowired
    ldapCrudImple ldap;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @GetMapping("/users")
    public Iterable<LdapUser> getAllUsers() {
        List <LdapUser> ldapUsers = ldap.retrieve();
        return ldapUsers;
    }

    @PostMapping( value = "/user" , consumes = "application/json") 
    public String setUpdate(@RequestBody LdapUser user) {
        String password = user.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        Boolean staus = ldap.update(user);
        return staus.toString();
    
    }

    @PostMapping(value="/remove/{email}")
    public String postUserRemove(@PathVariable("email") String username) {
        Boolean status = ldap.remove(username);
        return status.toString();
    }
}