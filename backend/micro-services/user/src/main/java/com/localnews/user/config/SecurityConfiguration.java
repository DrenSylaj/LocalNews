package com.localnews.user.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Component
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize -> Authorize
//                        .requestMatchers("/api/admin/**").hasAnyRole("ADMIN")
//                        .requestMatchers("/api/user/**").hasAnyRole("USER")
                        .requestMatchers("/api/v1/autori/**").hasAnyRole("AUTHOR")
//                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable);
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }


}
