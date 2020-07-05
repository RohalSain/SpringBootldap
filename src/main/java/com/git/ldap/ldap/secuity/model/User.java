package com.git.ldap.ldap.secuity.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

import org.springframework.data.couchbase.core.mapping.Document;


@Document
public class User {

    @Id
    private String id;

    @Field
    private String username;

    @Field
    private String password;

    private int active;

    @Field
    private String roles = "";

    @Field
    private String permissions = "";

    public User(String cid,String username, String password, String roles, String permissions){
        this.id = cid;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = 1;
    }

    protected User(){}

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
}
