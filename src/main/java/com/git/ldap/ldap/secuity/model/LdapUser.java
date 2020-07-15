package com.git.ldap.ldap.secuity.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


public class LdapUser {
    
    @NotBlank
    @NotEmpty
    @NotNull(message = "username is required")
    public String username;

    @NotBlank
    @NotEmpty
    @NotNull(message = "username is required")
    public String password;

    public LdapUser() { }

    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
}