package com.daniyar.ecommerce.domain.user.controller;

import com.daniyar.ecommerce.domain.user.dto.RegisterRequest;
import com.daniyar.ecommerce.domain.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // Register new user
    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {

        authService.register(request);

    }
}