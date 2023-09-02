package com.allianz.healthtourism.configuration;

import com.allianz.healthtourism.util.security.JWTFilter;
import com.allianz.healthtourism.util.security.SecurityService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    private final JWTFilter filter;
    private final SecurityService uds;
    public SecurityConfiguration(JWTFilter filter, SecurityService uds) {
        this.filter = filter;
        this.uds = uds;
    }


    private static final String[] AUTH_WHITELIST = {
            "/auth/**",
            "/swagger-ui/**",
            "v3/api-docs/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/api/public/**",
            "/api/public/authenticate",
            "/actuator/*",
            "/swagger-ui/**",
            "/admin-auth/**",
            "/admin-auth",
            "/doctor-auth/**",
            "/doctor-auth",
            "/patient-auth/**",
            "/patient-auth"

    };
    private static final String[] ADMIN_WHITELIST = {
            "/country/**",
            "/country",
            "/hospital/**",
            "/hospital"
    };

    private static final String[] ADMIN_PATIENT_COMMON_WHITELIST = {
            "/city/**",
            "/city",
            "/flight/**",
            "/flight",
            "/hotel/**",
            "/hotel",
            "/flight-booking/**",
            "/flight-booking",
            "/hotel-booking/**",
            "/hotel-booking",
            "/travel-plan/**",
            "/travel-plan"
    };
    private static final String[] ADMIN_DOCTOR_COMMON_WHITELIST = {
            "/medical-record/**",
            "/medical-record"
    };
    private static final String[] COMMON_WHITELIST = {
            "/doctor/**",
            "/doctor",
            "/patient/**",
            "/patient",
            "/appointment/**",
            "/appointment"
    };




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("security");
        http.headers().frameOptions().disable();
        http.csrf().disable()
                .httpBasic().disable()
                .cors()
                .configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of("*"));
                    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                    configuration.setAllowedHeaders(List.of("*"));
                    configuration.setExposedHeaders(List.of("Content-Disposition"));
                    return configuration;
                }).and()
                .authorizeHttpRequests()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .requestMatchers(ADMIN_WHITELIST).hasRole("admin")
                .requestMatchers(COMMON_WHITELIST).hasAnyRole("admin","patient","doctor")
                .requestMatchers(ADMIN_PATIENT_COMMON_WHITELIST).hasAnyRole("admin", "patient")
                .requestMatchers(ADMIN_DOCTOR_COMMON_WHITELIST).hasAnyRole("admin", "doctor")
                .and()
                .userDetailsService(uds)
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                        }

                )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}