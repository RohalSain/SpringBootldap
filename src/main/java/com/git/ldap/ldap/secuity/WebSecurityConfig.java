package com.git.ldap.ldap.secuity;

import com.git.ldap.ldap.secuity.model.CustomUserDetailsMapper;

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
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
  @Autowired
  MyAuthoritiesPopulator myUserDetailService;

  @Autowired
  CustomUserDetailsMapper mycustomeDetailMapper;

  
  @Autowired
  UserService myUserDetailServicerem;
  
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
      http.authorizeRequests().antMatchers("/userCreate","/users","/user","/remove/**").permitAll().and()
      .authorizeRequests() 
.anyRequest().authenticated() 
.and() 
.formLogin() 
.loginPage("/login") 
.usernameParameter("user") 
.passwordParameter("pwd") 
.defaultSuccessUrl("/dashboard", true) 
.failureForwardUrl("/login") 
.permitAll() 
.and() 
.logout() 
.logoutSuccessUrl("/") 
.and() 
.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and() 
.rememberMe().tokenValiditySeconds(86400).and().userDetailsService(myUserDetailServicerem);

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



