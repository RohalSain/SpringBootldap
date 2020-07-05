package com.git.ldap.ldap.secuity;

import com.git.ldap.ldap.secuity.model.User;
import com.git.ldap.ldap.secuity.model.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User user = new User("rohal","rohal123", new ArrayList <>()); 
        //UserPrincipal userPrincipal = new UserPrincipal(user);
        //return userPrincipal;
        User user = userRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }
    
}