package com.thehecklers.secplay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@EnableWebSecurity
public class SecConfig extends WebSecurityConfigurerAdapter {
    //private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);   // MAH: BCrypt is a good, self-contained option
    private PasswordEncoder passwordEncoder = new SCryptPasswordEncoder();      // SCrypt requires Bouncy Castle dep in pom

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/index").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/app").authenticated()
                .and()
                .formLogin();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication().passwordEncoder(passwordEncoder)
                .withUser("mark").password(passwordEncoder.encode("badpw")).roles("USER")
                .and()
                .withUser("robw").password(passwordEncoder.encode("betterpw")).roles("USER", "ADMIN");
    }
}
