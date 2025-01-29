package com.secomext.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity<Map<String, Object>> login(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "OK");
        response.put("user", authentication.getName());
        response.put("roles", authentication.getAuthorities());
        response.put("sessionId", session.getId());
        return ResponseEntity.ok(response);
    }

    /*
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
    }*/
}
