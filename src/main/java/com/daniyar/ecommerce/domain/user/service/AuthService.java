package com.daniyar.ecommerce.domain.user.service;

import com.daniyar.ecommerce.domain.user.dto.RegisterRequest;
import com.daniyar.ecommerce.domain.user.entity.Role;
import com.daniyar.ecommerce.domain.user.entity.User;
import com.daniyar.ecommerce.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Register new user
    public void register(RegisterRequest request) {

        User user = new User();

        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
    }
}