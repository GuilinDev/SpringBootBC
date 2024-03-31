package org.guilin.ollama.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@Slf4j
public class LocalOllamaController {

    private final OllamaChatClient chatClient;

    @Autowired
    public LocalOllamaController(OllamaChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/ai/ollama")
    public Map generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        log.debug("Received message in /ai/ollama: {}", message);
        return Map.of("generation", chatClient.call(message));
    }

    @GetMapping("/ai/ollamaStream")
    public Flux<ChatResponse> generateStream(
            @RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        log.debug("Received message in /ai/ollamaStream: {}", message);
        Prompt prompt = new Prompt(new UserMessage(message));
        return chatClient.stream(prompt);
    }
}
