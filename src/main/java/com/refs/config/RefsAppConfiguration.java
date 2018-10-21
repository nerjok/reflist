package com.refs.config;




import lombok.extern.slf4j.Slf4j;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Slf4j
@Configuration
@EnableWebSecurity
public class RefsAppConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthentificationHandler successHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    public RefsAppConfiguration(AuthentificationHandler successHandler, UserDetailsService userDetailsService) {
        this.successHandler = successHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/assets/**", "/webjars/**", "/users/register", "/categories", "/advertisement/*/show").permitAll()
                    .antMatchers("/login").anonymous()
                .antMatchers("/advertisement/new").authenticated()
                .antMatchers("/categories/**", "/category/*").hasAuthority("ADMIN")
                .antMatchers(("/users/**")).hasAuthority("ADMIN")
                //.antMatchers("/users/**").access("hasRole('ADMIN') or hasRole('ROLE_ADMIN')")
                .antMatchers("/advertisement/", "/advertisement").hasAuthority("ADMIN")
                .antMatchers("/advertisement/**").hasAnyAuthority("ADMIN, USER") //.hasRole("USER").and().exceptionHandling().accessDeniedPage("/")
                    .and()

                .formLogin()
                .successHandler(successHandler)
                .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }
/*
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
*/
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}
/*

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.*;

@Configuration
@EnableWebSecurity
public class RefsAppConfiguration  extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}
*/