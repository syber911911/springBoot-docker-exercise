package com.example.springbootdockerexercise.Controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
@RestController
public class HelloController {
    @GetMapping("/")
    public String getMyMemberInfo() {
        return getString(LocalDateTime.now().toString());
        // return "hello";
    }

    private static String getString(String string) {
        // 웹훅 URL
        String webhookUrl = "https://discord.com/api/webhooks/1090549186040184913/_nVKm8TfNkMjjZDz2lgNqbVQBmciLcgii6trO7nXIEz7LAtMHVRBXkSVwoSd-nYRGxzU";

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 바디 설정
        String payload = "{\"username\": \"syber911911\", \"content\": \"" + string + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(payload, headers);

        // RestTemplate을 사용하여 POST 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(webhookUrl, HttpMethod.POST, entity, String.class);

        // 응답 처리
        if (response.getStatusCode().is2xxSuccessful()) {
            return "Webhook sent successfully";
        } else {
            return "Failed to send webhook";
        }
    }

    @GetMapping("/hook/{string}")
    public String sendHookWithString(@PathVariable("string") String string) {
        return getString(string);
    }
}
