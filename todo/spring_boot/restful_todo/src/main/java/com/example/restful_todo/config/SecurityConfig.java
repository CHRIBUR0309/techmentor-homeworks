package com.example.restful_todo.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(login -> login.loginProcessingUrl("/todo_items/sign_in")
                .loginPage("/todo_items/sign_in").defaultSuccessUrl("/todo_items")
                .failureUrl("/todo_items/sign_in?error").permitAll());
        return httpSecurity.build();
    }
}
