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
    protected void configure(HttpSecurity http) throws Exception {
      http
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
        .rememberMe().tokenValiditySeconds(86400);
    }
  
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.
      ldapAuthentication()
          .userDetailsContextMapper(mycustomeDetailMapper)
          .userDnPatterns("uid={0},ou=people")
          .groupSearchBase("ou=groups")
          .contextSource()
            .url("ldap://localhost:8389/dc=springframework,dc=org")
            .and()  
          .passwordCompare()
            .passwordEncoder(new BCryptPasswordEncoder())
            .passwordAttribute("userPassword")
            .and().ldapAuthoritiesPopulator(myUserDetailService)
            .and().userDetailsService(myUserDetailServicerem);     
    } 

    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
    }    
  }



