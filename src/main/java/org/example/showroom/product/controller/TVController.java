package org.example.showroom.product.controller;


import lombok.RequiredArgsConstructor;
import org.example.showroom.product.dto.TVDto;
import org.example.showroom.product.entity.TV;
import org.example.showroom.product.entity.TVResolution;
import org.example.showroom.product.service.TVService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/tv")
@RequiredArgsConstructor
public class TVController {
    private final TVService tvService;

    @GetMapping
    public ResponseEntity<List<TV>> getTvs() {
        List<TV> refrigerators = Arrays.asList(
                new TV("2024 QLED 4K QDE1 (214 cm) 풀 모션 슬림핏 벽걸이형", 214, 41.5,
                        TVResolution.UHD_4K, 60, "1등급", 98.2,
                        2, "4K 화질 업스케일링"),

                new TV("2024 QLED 4K QDE1 (163 cm) 풀 모션 슬림핏 벽걸이형", 163, 20.9,
                        TVResolution.UHD_4K, 60, "1등급", 78.2,
                        2, "4K 화질 업스케일링"),
                new TV("2024 The Serif (163 cm)", 163, 36.6,
                        TVResolution.UHD_4K, 120, "3등급", 132.9,
                        4, "있음"),
                new TV("2024 The Serif (125 cm)", 163, 20.8,
                        TVResolution.UHD_4K, 120, "3등급", 102.6,
                        4, "있음")
        );

        // 상태 코드 200 (OK)와 함께 리스트를 ResponseEntity로 감싸서 반환
        return new ResponseEntity<>(refrigerators, HttpStatus.OK);
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<TVDto>> fetchRefrigerators() {
        List<TVDto> tvs = tvService.fetchRefrigeratorsFromAI();
        return ResponseEntity.ok(tvs);
    }

}
