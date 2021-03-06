package com.git.ldap.ldap.Controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.git.ldap.ldap.LdapSecurity.ldapCrudImple;
import com.git.ldap.ldap.Repository.UserRepository;
import com.git.ldap.ldap.Model.LdapUser;
import com.git.ldap.ldap.Model.Login;
import com.git.ldap.ldap.Model.User;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Deprecated
@Controller
public class controlleResetPassword {
    
    @Autowired
    ldapCrudImple ldap;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/resetPassword")
    public String ResetPassword(Model model) {
        model.addAttribute("userUpdate", new LdapUser());
        return "resetPassword";
    }

    @PostMapping("/resetPassword")
    public String postResetPassword(@Valid LdapUser user , Model model,HttpServletRequest request,HttpServletResponse response){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        ldap.update(user);
        User updatedUser = userRepository.findByUsername(user.getUsername());
        updatedUser.setPasswordStatusChange(true);
        userRepository.save(updatedUser);
        Cookie[] cookies = (Cookie[]) request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("remember-me") ) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:/login";
    }
    
    @GetMapping("/dashboard")
    public String getHomePage() {
        return "dashboard";
    }

    @GetMapping("/index")
    public String index() {
        return "welcome";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login",new Login());
        model.addAttribute("status","false");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "dashboard";
        } else {
            return "login";
        }
        
    }

    @PostMapping("/login")
    public String postlogin(@Valid Login login,BindingResult errors, Model model) {
    
        
            model.addAttribute("status","true"); 
            model.addAttribute("error", "Username/Password error");
            return "login";
    }
    @GetMapping("/logout")
    public String getLogOut() {
        return "logout";
    }



    @GetMapping("/userCreate")
    public String createldapUser(Model model) {
        model.addAttribute("userCreation", new LdapUser());
        return "user";
    }

    @PostMapping("/userCreate")
    public String postlogin(@Valid LdapUser user ,BindingResult errors, Model model) {
        
        String password = user.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        boolean status =  ldap.create(user);
        User DataUser = new User();
        DataUser.setId(DataUser.UniqueIdGeneration());
        DataUser.setPassword(hashedPassword);
        DataUser.setUsername(user.getUsername());
        DataUser.setActive(1);    
         if(status) {
             userRepository.save(DataUser);
         } else {
            //  error display with Save page
         }
        return "redirect:/login";
}

    @GetMapping("/deleteUser")
    public String getDeleteUser(Model model) {
        model.addAttribute("deleteUser", new LdapUser());
        return "deleteUser";
    }
    @PostMapping("/deleteUser")
    public String postDeleteUser(@Valid LdapUser user , Model model,HttpServletRequest request,HttpServletResponse response){
        ldap.remove(user.getUsername());
        User updatedUser = userRepository.findByUsername(user.getUsername());
        updatedUser.setPasswordStatusChange(true);
        userRepository.save(updatedUser);
        Cookie[] cookies = (Cookie[]) request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("remember-me") || cookie.getName().equals("JSESSIONID") ) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:/login";
    }
}