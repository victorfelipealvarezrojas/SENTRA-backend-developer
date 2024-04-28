package com.valvarez.evaluation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/users/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/users/**").permitAll()
                ).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
