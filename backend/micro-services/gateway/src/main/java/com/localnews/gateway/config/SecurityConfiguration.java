package com.localnews.gateway.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter){
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
                .authorizeExchange()
                .pathMatchers("/api/v1/autori/**").hasRole("AUTHOR")
                .pathMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .pathMatchers("/api/v1/auth/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION); gi

        return http.build();
    }

}
