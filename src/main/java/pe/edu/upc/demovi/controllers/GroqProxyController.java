package pe.edu.upc.demovi.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/ia")
public class GroqProxyController {

    private final RestClient restClient = RestClient.create();

    @Value("${groq.api.key}")
    private String groqApiKey;

    @Value("${groq.api.url}")
    private String groqUrl;

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody String body) {
        return restClient.post()
                .uri(groqUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + groqApiKey)
                .body(body)
                .retrieve()
                .toEntity(String.class);
    }
}