package org.guilin.ollama.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class SimpleChatController {

    private final ChatClient chatClient;

    @Autowired
    public SimpleChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/ai/chat")
    public Map<String, String> completion(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        log.debug("Received message in /ai/chat: {}", message);
        return Map.of("generation", chatClient.call(message));
    }
}
