package com.jmf.secomext.pruebapractica.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.annotation.Secured;
@RestController
@RequestMapping("/api/ping")
public class PingController {

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public String ping() {
        return "pong";
    }

}
