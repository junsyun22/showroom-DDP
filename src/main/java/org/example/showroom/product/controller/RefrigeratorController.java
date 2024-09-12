package org.example.showroom.product.controller;

// RefrigeratorController.java
import org.example.showroom.product.dto.RefrigeratorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/refrigerators")
public class RefrigeratorController {

    @GetMapping
    public ResponseEntity<List<RefrigeratorDto>> getRefrigerators() {
        List<RefrigeratorDto> refrigerators = Arrays.asList(
                new RefrigeratorDto("BESPOKE 냉장고 4도어 902L", "BESPOKE 냉장고 4도어",
                        "프리스탠딩", "912×1830×922 mm", 149, 902,
                        536, 366, "1등급", 44.4),
                new RefrigeratorDto("양문형 냉장고 846L", "양문형 냉장고",
                        "프리스탠딩", "912×1780×915 mm", 137, 846,
                        526, 320, "1등급", 40.7),
                new RefrigeratorDto("삼성 냉장고 800L", "양문형 냉장고",
                        "프리스탠딩", "900×1800×900 mm", 135, 800,
                        500, 300, "1등급", 39.5),
                new RefrigeratorDto("LG 냉장고 820L", "양문형 냉장고",
                        "프리스탠딩", "910×1820×920 mm", 140, 820,
                        510, 310, "2등급", 42.0)
        );

        // 상태 코드 200 (OK)와 함께 리스트를 ResponseEntity로 감싸서 반환
        return new ResponseEntity<>(refrigerators, HttpStatus.OK);
    }
}
