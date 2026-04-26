package com.ai.intelligence.util;

import com.ai.intelligence.dto.LoginRequest;
import com.ai.intelligence.dto.RegisterRequest;
import com.ai.intelligence.model.User;
import com.ai.intelligence.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = { "http://localhost:5173",
        "https://aicore-latest.vercel.app" }, allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
                RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {
        User user = new User();
        user.setName(req.name);
        user.setEmail(req.email);
        user.setPassword(req.password);

        return service.register(user);
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest req) {
        return service.login(req.email, req.password)
                .map(user -> "Login Success")
                .orElse("Invalid Credentials");
    }
}
