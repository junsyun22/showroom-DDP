package org.example.showroom.product.dto;

import lombok.Data;

// RefrigeratorDTO.java
@Data
public class RefrigeratorDto {
    private String productName; // 제품명
    private String productType; // 제품 타입
    private String installationType; // 설치 타입
    private String dimensions; // 크기
    private double weight; // 무게
    private int totalCapacity; // 전체 용량
    private int fridgeCapacity; // 냉장실 용량
    private int freezerCapacity; // 냉동실 용량
    private String energyEfficiencyRating;// 소비 효율 등급
    private double powerConsumption; // 소비전력

    public RefrigeratorDto(String productName, String productType, String installationType,
                           String dimensions, double weight, int totalCapacity,
                           int fridgeCapacity, int freezerCapacity,
                           String energyEfficiencyRating, double powerConsumption) {
        this.productName = productName;
        this.productType = productType;
        this.installationType = installationType;
        this.dimensions = dimensions;
        this.weight = weight;
        this.totalCapacity = totalCapacity;
        this.fridgeCapacity = fridgeCapacity;
        this.freezerCapacity = freezerCapacity;
        this.energyEfficiencyRating = energyEfficiencyRating;
        this.powerConsumption = powerConsumption;
    }

    // Getters 및 Setters
    // 생략 가능 (Lombok @Data 어노테이션 사용 시 불필요)
}
