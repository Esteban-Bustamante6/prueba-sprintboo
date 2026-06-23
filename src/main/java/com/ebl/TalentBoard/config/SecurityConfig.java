package com.ebl.TalentBoard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF completamente para pruebas
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // H2 console
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // PERMITIR ACCESO TOTAL A CUALQUIER URL
                );

        return http.build();
    }
}