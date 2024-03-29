package com.orjuelalaley.Pizzeria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(customizeRequests -> {
                            customizeRequests
                                    .requestMatchers(HttpMethod.GET, "/api/pizzas/available").hasAnyRole("ADMIN","CUSTOMER")
                                    .requestMatchers(HttpMethod.POST, "/api/pizzas/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.PUT, "/api/pizzas/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE, "/api/orders/random").hasAnyAuthority("RANDOM_ORDER")
                                    .requestMatchers(HttpMethod.DELETE, "/api/pizzas/**").hasRole("ADMIN")
                                    .requestMatchers("/api/orders/**").hasRole("ADMIN")
                                    .anyRequest()
                                    .authenticated();
                        }
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
