package com.ai.intelligence.util;

import com.ai.intelligence.dto.LoginRequest;
import com.ai.intelligence.dto.RegisterRequest;
import com.ai.intelligence.model.User;
import com.ai.intelligence.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://aicore-latest.vercel.app"
})
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());

        service.register(user); // ✅ SAVE USER

        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return service.login(req.getEmail(), req.getPassword())
                .map(user -> ResponseEntity.ok("Login Success"))
                .orElse(ResponseEntity.status(401).body("Invalid Credentials"));
    }
}