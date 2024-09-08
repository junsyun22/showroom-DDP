package org.example.showroom.Product.Refrigerator;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Refrigerator {
    private String name;
    private String model;
    private int totalCapacity;
    private int fridgeCapacity;
    private int freezerCapacity;
    private String energyEfficiency;
    private int powerConsumption;
    private String countryOfManufacture;
    private int yearOfRelease;

    // 생성자, Getter/Setter 메서드
    public Refrigerator(String name, String model, int totalCapacity, int fridgeCapacity, int freezerCapacity, String energyEfficiency, int powerConsumption, String countryOfManufacture, int yearOfRelease) {
        this.name = name;
        this.model = model;
        this.totalCapacity = totalCapacity;
        this.fridgeCapacity = fridgeCapacity;
        this.freezerCapacity = freezerCapacity;
        this.energyEfficiency = energyEfficiency;
        this.powerConsumption = powerConsumption;
        this.countryOfManufacture = countryOfManufacture;
        this.yearOfRelease = yearOfRelease;
    }


}