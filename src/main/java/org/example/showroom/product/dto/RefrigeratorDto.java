package org.example.showroom.product.dto;

import lombok.Data;
import org.example.showroom.product.entity.Refrigerator;

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

    public Refrigerator toEntity() {
        return new Refrigerator(
                this.productName,
                this.productType,
                this.installationType,
                this.dimensions,
                this.weight,
                this.totalCapacity,
                this.fridgeCapacity,
                this.freezerCapacity,
                this.energyEfficiencyRating,
                this.powerConsumption
        );
    }

    public static RefrigeratorDto fromEntity(Refrigerator refrigerator) {
        RefrigeratorDto dto = new RefrigeratorDto();
        dto.setProductName(refrigerator.getProductName());
        dto.setProductType(refrigerator.getProductType());
        dto.setInstallationType(refrigerator.getInstallationType());
        dto.setDimensions(refrigerator.getDimensions());
        dto.setWeight(refrigerator.getWeight());
        dto.setTotalCapacity(refrigerator.getTotalCapacity());
        dto.setFridgeCapacity(refrigerator.getFridgeCapacity());
        dto.setFreezerCapacity(refrigerator.getFreezerCapacity());
        dto.setEnergyEfficiencyRating(refrigerator.getEnergyEfficiencyRating());
        dto.setPowerConsumption(refrigerator.getPowerConsumption());
        return dto;
    }

    // Getters 및 Setters
    // 생략 가능 (Lombok @Data 어노테이션 사용 시 불필요)
}
