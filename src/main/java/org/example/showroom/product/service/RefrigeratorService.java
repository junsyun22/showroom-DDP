package org.example.showroom.product.service;

import lombok.RequiredArgsConstructor;
import org.example.showroom.product.dto.RefrigeratorDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefrigeratorService {

    private final WebClient.Builder webClientBuilder;

    public List<RefrigeratorDto> fetchRefrigeratorsFromAI() {
        String aiServerUrl = "http://metaai.iptime.org:8282";

        Mono<RefrigeratorDto[]> refrigeratorListMono = webClientBuilder.build()
                .get()
                .uri(aiServerUrl)
                .retrieve()
                .bodyToMono(RefrigeratorDto[].class);

        RefrigeratorDto[] refrigeratorDtos = refrigeratorListMono.block();

        return Arrays.asList(refrigeratorDtos);
    }
}