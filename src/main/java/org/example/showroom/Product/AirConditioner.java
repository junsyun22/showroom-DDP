package org.example.showroom.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirConditioner {
    private String name;
    private String model;
    private String coolingArea;
    private String energyEfficiency;

    // 생성자, Getter/Setter 메서드
    public AirConditioner(String name, String model, String coolingArea, String energyEfficiency) {
        this.name = name;
        this.model = model;
        this.coolingArea = coolingArea;
        this.energyEfficiency = energyEfficiency;
    }


}