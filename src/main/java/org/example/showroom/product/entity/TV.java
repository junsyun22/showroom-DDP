package org.example.showroom.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName; // 제품명
    private int productSize; // 사이즈(cm)
    private double weight;
    private TVResolution resolution; // 해상도
    private int refreshRate; // 주사율
    private String energyEfficiencyRating; // 소비 효율 등급
    private double powerConsumption; // 소비전력
    private int speakerChannels; // 스피커 채널 수
    private String upScaling; // 화질 업스케일링


    protected TV() {
    }

    public TV(String productName, int productSize, double weight,
              TVResolution resolution, int refreshRate, String energyEfficiencyRating,
              double powerConsumption, int speakerChannels, String upScaling) {
        this.productName = productName;
        this.productSize = productSize;
        this.weight = weight;
        this.resolution = resolution;
        this.refreshRate = refreshRate;
        this.energyEfficiencyRating = energyEfficiencyRating;
        this.powerConsumption = powerConsumption;
        this.speakerChannels = speakerChannels;
        this.upScaling = upScaling;
    }

    public double getInchSize() {
        return productSize/2.54;
    }
}
