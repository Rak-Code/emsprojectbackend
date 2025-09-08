package com.laitusneo.backend.controller;

import com.laitusneo.backend.dto.LoginRequest;
import com.laitusneo.backend.dto.RegisterRequest;
import com.laitusneo.backend.dto.AuthResponse;
import com.laitusneo.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "User authentication endpoints")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Create a new user account")
    public String register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user and return JWT token")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}
