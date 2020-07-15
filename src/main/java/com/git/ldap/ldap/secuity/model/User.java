package com.git.ldap.ldap.secuity.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import com.git.ldap.ldap.secuity.UserRepository;

import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document
public  class User {

    

    @Id
    private String id;

    @NotBlank(message = "enter valid username")
    @Field
    private String username;

    @NotBlank(message ="enter valdi password")
    @Field
    private String password;

    @Field
    private int active;


    @Field
    private String email;

    @Field
    private String roles = "";

    @Field
    private String permissions = "";

    public static User getInstance(String username,UserRepository userRepository) 
    {      
        return userRepository.findByUsername(username);
    } 

    public User(String cid,String username, String password, String roles, String permissions){
        this.id = cid;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = 1;
    }

    public User(){}
    public User(String username,String password,String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getActive() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
    
    public String UniqueIdGeneration() {
        return  UUID.randomUUID().toString(); 
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setActive(int active) {
        this.active = active;
    }
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}
