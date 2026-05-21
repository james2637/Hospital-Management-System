package com.rabibanik.hospitalManagementSystem.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        httpSecurity.
                authorizeHttpRequests(request -> request
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/hospital/**").hasRole("ADMIN")
                        .requestMatchers("/doctor/**").hasAnyRole("DOCTOR", "ADMIN")
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return provider;
    }

}
