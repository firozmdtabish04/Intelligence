package com.ai.intelligence.service;

import com.ai.intelligence.model.User;
import com.ai.intelligence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    public String register(User user) {
        if (repo.findByEmail(user.getEmail()).isPresent()) {
            return "User already exists";
        }
        repo.save(user);
        return "User registered successfully";
    }

    public Optional<User> login(String email, String password) {
        Optional<User> user = repo.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }
}
