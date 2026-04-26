// package com.ai.intelligence.controller;

// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.reactive.function.client.WebClient;

// import java.util.Map;

// @RestController
// @RequestMapping("/api/chat")
// @CrossOrigin(origins = "http://localhost:5173")
// public class ChatController {

// private final WebClient webClient;

// public ChatController() {
// this.webClient = WebClient.builder()
// .baseUrl("http://localhost:11434")
// .build();
// }

// @PostMapping
// public Map<String, String> chat(@RequestBody Map<String, String> request) {

// String prompt = request.get("message");

// Map<String, Object> body = Map.of(
// "model", "llama3",
// "prompt", prompt,
// "stream", false);

// try {
// Map response = webClient.post()
// .uri("/api/generate")
// .bodyValue(body)
// .retrieve()
// .bodyToMono(Map.class) // ✅ parse JSON directly
// .block();

// String reply = response != null
// ? response.get("response").toString()
// : "No response from AI";

// return Map.of("reply", reply);

// } catch (Exception e) {
// return Map.of("reply", "Error: Unable to reach AI server");
// }
// }
// }

package com.ai.intelligence.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") // allow frontend
@RestController
@RequestMapping("/api")
public class ChatController {

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody Map<String, String> request) {
        String message = request.get("message");

        return Map.of("reply", "AI says: " + message);
    }
}