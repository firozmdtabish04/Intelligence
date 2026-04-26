package com.ai.intelligence.controller;

import com.ai.intelligence.dto.ChatRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = { "http://localhost:5173",
        "https://aicore-latest.vercel.app" }, allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
                RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
public class ChatController {

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {

        // 🔥 Replace this with Ollama/OpenAI later
        return "AI Response for: " + request.message;
    }
}