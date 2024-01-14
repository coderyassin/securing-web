package org.yascode.securingweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.yascode.securingweb.filters.CustomBasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final CustomBasicAuthenticationFilter customBasicAuthenticationFilter;

    public SecurityConfig(RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                          CustomBasicAuthenticationFilter customBasicAuthenticationFilter) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.customBasicAuthenticationFilter = customBasicAuthenticationFilter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                    .requestMatchers("/", "/home").permitAll()
                    .requestMatchers("/student/*").hasAuthority("READ_PRIVILEGE")
                    .requestMatchers("/football/*").hasAuthority("WRITE_PRIVILEGE")
                    .anyRequest().authenticated()
                )
                .addFilterBefore(customBasicAuthenticationFilter, BasicAuthenticationFilter.class)
                .httpBasic(basic -> basic.authenticationEntryPoint(restAuthenticationEntryPoint))
                .exceptionHandling(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
