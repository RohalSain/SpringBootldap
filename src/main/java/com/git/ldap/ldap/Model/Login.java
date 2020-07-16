package com.git.ldap.ldap.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Login {
    
    @NotBlank
    @NotEmpty
    public String user;

    @NotBlank
    @NotEmpty
    @NotNull(message="Password field should not be blank")
    public String pwd; 
}