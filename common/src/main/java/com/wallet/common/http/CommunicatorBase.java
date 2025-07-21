package com.wallet.common.http;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

public abstract class CommunicatorBase {
    protected final WebClient webClient;

    protected CommunicatorBase(String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    protected <T> Mono<T> get(String uri, Class<T> responseType) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(WebClientResponseException.class, ex -> Mono.error(new RuntimeException("GET request failed: " + ex.getMessage(), ex)));
    }

    protected <T, R> Mono<R> post(String uri, T requestBody, Class<R> responseType) {
        return webClient.post()
                .uri(uri)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(WebClientResponseException.class, ex -> Mono.error(new RuntimeException("POST request failed: " + ex.getMessage(), ex)));
    }

    protected <T, R> Mono<R> put(String uri, T requestBody, Class<R> responseType){
        return webClient.put()
                .uri(uri)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(WebClientResponseException.class, ex -> Mono.error(new RuntimeException("PUT request failed: " + ex.getMessage(), ex)));
    }
} 