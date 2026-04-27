package com.ai.intelligence.service;

import com.ai.intelligence.model.User;
import com.ai.intelligence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ REGISTER
    public void register(User user) {

        // Check duplicate email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // 🔐 Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    // ✅ LOGIN
    public Optional<User> login(String email, String password) {

        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // 🔐 Compare encrypted password
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }
}