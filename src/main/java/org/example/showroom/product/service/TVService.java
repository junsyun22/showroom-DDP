package org.example.showroom.product.service;

import lombok.RequiredArgsConstructor;
import org.example.showroom.product.dto.TVDto;
import org.example.showroom.product.entity.TV;
import org.example.showroom.product.repository.TVRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TVService {

    private final WebClient.Builder webClientBuilder;
    private final TVRepository tvRepository;

    public List<TVDto> fetchRefrigeratorsFromAI() {
        String aiServerUrl = "http://metaai.iptime.org:8282";

        Mono<TVDto[]> refrigeratorListMono = webClientBuilder.build()
                .get()
                .uri(aiServerUrl)
                .retrieve()
                .bodyToMono(TVDto[].class);

        TVDto[] tvDtos;
        try {
            tvDtos = refrigeratorListMono.block();
        } catch (Exception e) {
            throw new RuntimeException("AI 서버 통신 중 오류 발생", e);
        }

        if (tvDtos != null) {
            Arrays.stream(tvDtos).forEach(dto -> {
                Optional<TV> existingTV = Optional.ofNullable(tvRepository
                        .findByProductName(dto.getProductName()));

                if (existingTV.isEmpty()) {
                    TV tv = dto.toEntity();
                    tvRepository.save(tv);
                }
            });
        }


        List<TV> savedTVs = tvRepository.findAll();
        return savedTVs.stream()
                .map(TVDto::fromEntity)
                .toList();
    }
}