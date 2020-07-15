package com.git.ldap.ldap.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.git.ldap.ldap.ldapCrudOperations.ldapCrudImple;
import com.git.ldap.ldap.secuity.model.LdapUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class controllerRest {

    @Autowired
    ldapCrudImple ldap;

    
    @GetMapping("/users")
    public Iterable<LdapUser> getAllUsers() {
        List <LdapUser> ldapUsers = ldap.retrieve();
        return ldapUsers;
    }

    @PostMapping( value = "/user" , consumes = "application/json") 
    public String setUpdate(@RequestBody LdapUser user) {
        //String password = user.getPassword();
        //String hashedPassword = new BCryptPasswordEncoder().encode(password).toString();
        //user.setPassword(hashedPassword);
        Boolean staus = ldap.update(user);
        return staus.toString();
    
    }

    @PostMapping(value="/remove/{email}")
    public String postUserRemove(@PathVariable("email") String username) {
        Boolean status = ldap.remove(username);
        return status.toString();
    }
}