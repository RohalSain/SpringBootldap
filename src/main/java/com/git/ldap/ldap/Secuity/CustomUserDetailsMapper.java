package com.git.ldap.ldap.Secuity;

import java.util.Collection;

import com.git.ldap.ldap.Repository.UserRepository;
import com.git.ldap.ldap.LdapSecurity.ldapCrudImple;
import com.git.ldap.ldap.Model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsMapper  extends LdapUserDetailsMapper {

    // @Autowired
    // private UserDetailsService userDetailService;

    @Autowired
    private UserRepository userRepository;  
    
    private ldapCrudImple ldap;


    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
            Collection<? extends GrantedAuthority> authorities) {
            //System.out.println("Name is"+username);
            //return (UserDetails) this.userDetailService.loadUserByUsername(username);

            //User user = User.getInstance(username,userRepository);
            
            User user = userRepository.findByUsername(username);
            user.setPasswordStatusChange(false);
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            return userPrincipal;
      
    }
    
}
    