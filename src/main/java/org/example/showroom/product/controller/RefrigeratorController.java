package org.example.showroom.product.controller;

// RefrigeratorController.java
import lombok.RequiredArgsConstructor;
import org.example.showroom.product.dto.RefrigeratorDto;
import org.example.showroom.product.entity.Refrigerator;
import org.example.showroom.product.service.RefrigeratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/refrigerators")
@RequiredArgsConstructor
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;

    @GetMapping
    public ResponseEntity<List<Refrigerator>> getRefrigerators() {
        List<Refrigerator> refrigerators = Arrays.asList(
                new Refrigerator("BESPOKE 냉장고 4도어 902L", "BESPOKE 냉장고 4도어",
                        "프리스탠딩", "912×1830×922 mm", 149, 902,
                        536, 366, "1등급", 44.4),
                new Refrigerator("양문형 냉장고 846L", "양문형 냉장고",
                        "프리스탠딩", "912×1780×915 mm", 137, 846,
                        526, 320, "1등급", 40.7),
                new Refrigerator("삼성 냉장고 800L", "양문형 냉장고",
                        "프리스탠딩", "900×1800×900 mm", 135, 800,
                        500, 300, "1등급", 39.5),
                new Refrigerator("LG 냉장고 820L", "양문형 냉장고",
                        "프리스탠딩", "910×1820×920 mm", 140, 820,
                        510, 310, "2등급", 42.0)
        );

        // 상태 코드 200 (OK)와 함께 리스트를 ResponseEntity로 감싸서 반환
        return new ResponseEntity<>(refrigerators, HttpStatus.OK);
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<RefrigeratorDto>> fetchRefrigerators() {
        List<RefrigeratorDto> refrigerators = refrigeratorService.fetchRefrigeratorsFromAI();
        return ResponseEntity.ok(refrigerators);
    }

}
