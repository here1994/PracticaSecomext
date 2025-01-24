package com.jmf.secomext.pruebapractica.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration 
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuración del HttpSecurity
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configuración CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login").permitAll() // Login público
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // ADMIN
                        .requestMatchers("/api/user/**").hasRole("USER") // USER
                        .anyRequest().authenticated() // Resto de rutas autenticadas
                )
                .formLogin(form -> form.disable())
                .httpBasic(withDefaults()); // Autenticación básica

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Contraseñas encriptadas
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        // Usuarios en memoria
        var user1 = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        var user2 = User.withUsername("user")
                .password(passwordEncoder.encode("user123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Configuración de CORS
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:4200"); // Permitir Angular en localhost
        config.addAllowedHeader("*"); // Permitir todos los encabezados
        config.addAllowedMethod("*"); // Permitir todos los métodos HTTP
        config.setAllowCredentials(true); // Permitir credenciales (cookies, etc.)

        // Asignar configuración CORS a todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

