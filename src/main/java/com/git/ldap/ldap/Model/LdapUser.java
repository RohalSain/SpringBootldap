package com.git.ldap.ldap.Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



public class LdapUser {
    
    @NotEmpty
    @NotNull(message = "username is required")
    public String username;

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