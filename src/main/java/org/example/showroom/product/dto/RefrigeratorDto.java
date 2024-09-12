package org.example.showroom.product.dto;

// RefrigeratorDTO.java
public class RefrigeratorDto {
    private String productName;
    private String productType;
    private String installationType;
    private String dimensions;
    private double weight;
    private int totalCapacity;
    private int fridgeCapacity;
    private int freezerCapacity;
    private String energyEfficiencyRating;
    private double powerConsumption;

    // 생성자
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
