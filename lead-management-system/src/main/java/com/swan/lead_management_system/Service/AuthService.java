package com.swan.lead_management_system.Service;

import com.swan.lead_management_system.Config.SecurityConfig;
import com.swan.lead_management_system.Entity.User;
import com.swan.lead_management_system.Exception.DuplicateEmailException;
import com.swan.lead_management_system.Exception.InvalidCredentialsException;
import com.swan.lead_management_system.Repository.UserRepository;
import com.swan.lead_management_system.Security.JwtService;
import com.swan.lead_management_system.dto.AuthResponse;
import com.swan.lead_management_system.dto.LoginRequest;
import com.swan.lead_management_system.dto.RegisterRequest;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, SecurityConfig securityConfig, JwtService jwtService) {

        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
        this.jwtService = jwtService;
    }


    @Transactional
    public void register(RegisterRequest request) {
        String email = request.email().trim().toLowerCase();
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email is already registred");
        }
        User user = new User();
        user.setName(request.name());
        user.setEmail(email);

        String passwordEncoder = securityConfig.passwordEncoder().encode(request.password());

        user.setPassword(passwordEncoder);

        userRepository.save(user);
    }


    public AuthResponse login(LoginRequest request) {

        String email = request.email().trim().toLowerCase();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!securityConfig.passwordEncoder().matches(request.password(), user.getPassword())) {

            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token, "Bearer", user.getId(), user.getName(), user.getEmail());
    }

}
