package com.git.ldap.ldap.secuity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.git.ldap.ldap.secuity.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Service;

@Service
public class MyAuthoritiesPopulator implements LdapAuthoritiesPopulator {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData,String username) {
        System.out.println("Name is "+username);
        User user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Extract list of permissions (name)
        user.getPermissionList().forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        });

        // Extract list of roles (ROLE_name)
        user.getRoleList().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
            authorities.add(authority);
        });

        return authorities;
    }

}