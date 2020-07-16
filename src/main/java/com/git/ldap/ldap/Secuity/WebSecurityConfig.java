package com.git.ldap.ldap.Secuity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    

  @Autowired
  CustomUserDetailsMapper mycustomeDetailMapper;

  
  @Autowired
  UserService myUserDetailServicerem;
  
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
      http.authorizeRequests().antMatchers("/userCreate","/users","/user","/remove/**").permitAll().anyRequest().authenticated() 
.and() 
.formLogin() 
.loginPage("/login") 
.usernameParameter("user") 
.passwordParameter("pwd") 
.defaultSuccessUrl("/dashboard", true) 
.failureForwardUrl("/login") 
.permitAll() 
.and() 
.logout().logoutUrl("/logout")
.logoutSuccessUrl("/login").deleteCookies("JSESSIONID").invalidateHttpSession(true); 

http.rememberMe().alwaysRemember(false).tokenValiditySeconds(86400).rememberMeParameter("remember-me").key("remember-me");

    }
  
    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
      auth.
      ldapAuthentication()
          .userDetailsContextMapper(mycustomeDetailMapper)
          .userDnPatterns("uid={0},ou=people")
          .groupSearchBase("ou=groups")
          .contextSource()
            .url("ldap://localhost:8389/dc=springframework,dc=org")
            .and()  
          .passwordCompare()
            .passwordEncoder(encoder())
            .passwordAttribute("userPassword");     
    } 

    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
    } 

    @Bean
    public PasswordEncoder encoder() {
      return new BCryptPasswordEncoder();
    }
    }



