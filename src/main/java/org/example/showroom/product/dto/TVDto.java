package org.example.showroom.product.dto;

import lombok.Data;
import org.example.showroom.product.entity.TV;
import org.example.showroom.product.entity.TVResolution;

@Data
public class TVDto {
    private String productName; // 제품명
    private int productSize; // 사이즈(cm)
    private double weight;
    private TVResolution resolution; // 해상도
    private int refreshRate; // 주사율
    private String energyEfficiencyRating; // 소비 효율 등급
    private double powerConsumption; // 소비전력
    private int speakerChannels; // 스피커 채널 수
    private String upScaling; // 화질 업스케일링

    public TV toEntity() {
        return new TV(
                this.productName,
                this.productSize,
                this.weight,
                this.resolution,
                this.refreshRate,
                this.energyEfficiencyRating,
                this.powerConsumption,
                this.speakerChannels,
                this.upScaling
        );
    }

    public static TVDto fromEntity(TV tv) {
        TVDto dto = new TVDto();
        dto.setProductName(tv.getProductName());
        dto.setProductSize(tv.getProductSize());
        dto.setWeight(tv.getWeight());
        dto.setResolution(tv.getResolution());
        dto.setEnergyEfficiencyRating(tv.getEnergyEfficiencyRating());
        dto.setPowerConsumption(tv.getPowerConsumption());
        dto.setSpeakerChannels(tv.getSpeakerChannels());
        dto.setUpScaling(tv.getUpScaling());
        return dto;
    }

}
