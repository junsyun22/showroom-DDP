package org.example.showroom.product.service;

import lombok.RequiredArgsConstructor;
import org.example.showroom.product.dto.RefrigeratorDto;
import org.example.showroom.product.entity.Refrigerator;
import org.example.showroom.product.repository.RefrigeratorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefrigeratorService {

    private final WebClient.Builder webClientBuilder;
    private final RefrigeratorRepository refrigeratorRepository;

    public List<RefrigeratorDto> fetchRefrigeratorsFromAI() {
        String aiServerUrl = "http://metaai.iptime.org:8282";

        Mono<RefrigeratorDto[]> refrigeratorListMono = webClientBuilder.build()
                .get()
                .uri(aiServerUrl)
                .retrieve()
                .bodyToMono(RefrigeratorDto[].class);

        RefrigeratorDto[] refrigeratorDtos;
        try {
            refrigeratorDtos = refrigeratorListMono.block();
        } catch (Exception e) {
            throw new RuntimeException("AI 서버 통신 중 오류 발생", e);
        }

        if (refrigeratorDtos != null) {
            Arrays.stream(refrigeratorDtos).forEach(dto -> {
                Optional<Refrigerator> existingRefrigerator = refrigeratorRepository
                        .findByProductName(dto.getProductName());

                if (existingRefrigerator.isEmpty()) {
                    Refrigerator refrigerator = dto.toEntity();
                    refrigeratorRepository.save(refrigerator);
                }
            });
        }


        List<Refrigerator> savedRefrigerators = refrigeratorRepository.findAll();
        return savedRefrigerators.stream()
                .map(RefrigeratorDto::fromEntity)
                .toList();
    }
}