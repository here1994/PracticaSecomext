package com.secomext.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Permitir acceso a Swagger y la consola de H2 sin autenticación
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**", "/login").permitAll()

                        // USER solo puede visualizar (GET) categorías y productos
                        .requestMatchers(HttpMethod.GET, "/api/categorias/**", "/api/productos/**").hasAnyRole("USER", "ADMIN")

                        // ADMIN puede realizar cualquier operación sobre categorías y productos
                        .requestMatchers("/api/categorias/**", "/api/productos/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF para pruebas en Postman y H2 Console
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configuración CORS
                .headers(headers -> headers.frameOptions().disable())  // Permitir H2 Console
                .formLogin(form -> form.disable())  // desabilitar el formulario de login predeterminado
                .httpBasic(withDefaults());  // Habilita autenticación básica

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Configuración de CORS
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:4200"); // Permitir Angular en localhost
        config.addAllowedHeader("*"); // Permitir todos los encabezados
        config.addAllowedMethod("*"); // Permitir todos los métodos HTTP
        config.addExposedHeader("Authorization"); // 🔥 Exponer Authorization en respuestas
        config.setAllowCredentials(true); // Permitir credenciales (cookies, etc.)

        // Asignar configuración CORS a todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
