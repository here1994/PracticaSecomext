package com.jmf.secomext.pruebapractica.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class LoginController {

    
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AuthRequest authRequest, HttpSession session) {
        // Imprimir credenciales en la consola
        System.out.println("Username: " + authRequest.getUsername());
        System.out.println("Password: " + authRequest.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "OK");
            response.put("user", authentication.getName());
            response.put("roles", authentication.getAuthorities());
            response.put("sessionId", session.getId());
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false); // Obtener sesión actual si existe

        if (session != null) {
            session.invalidate(); // Invalidar la sesión
        }

        // Eliminar la cookie JSESSIONID
        var cookie = new jakarta.servlet.http.Cookie("JSESSIONID", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // Cambiar a true si usas HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expira inmediatamente
        response.addCookie(cookie);

        Map<String, String> result = new HashMap<>();
        result.put("message", "Logout exitoso");

        return ResponseEntity.ok(result);
    }
}

class AuthRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}