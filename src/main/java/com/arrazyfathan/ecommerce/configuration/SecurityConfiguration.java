package com.arrazyfathan.ecommerce.configuration;

import com.arrazyfathan.ecommerce.component.AuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private static final String[] ALLOWED = {
            "/api/v1/auth/login",
            "/api/v1/auth/register",
    };

    private final AuthFilter authFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry ->
                        registry.requestMatchers(ALLOWED)
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(managementConfigurer ->
                        managementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
