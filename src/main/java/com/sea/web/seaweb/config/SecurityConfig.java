package com.sea.web.seaweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

/**
 * Provides user authentication and authorization through Spring Security and configures pages access.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        // TODO: Social authentication.
        // TODO: Authentication from database.

        // In memory authentication for development
        auth.inMemoryAuthentication().withUser(User.withDefaultPasswordEncoder()
                .username("w@w.com").password("w").roles("USER"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .usernameParameter("email").passwordParameter("password")
                .loginPage("/signin")
                .failureUrl("/signin/error")
                .and()
                .logout()
                .logoutSuccessUrl("/signin")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .and().
                csrf().disable(); // CSRF on blocks angular requests
    }

}
