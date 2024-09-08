package org.example.showroom.Product.TV;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TV {
    private String name;
    private String model;
    private String resolution;
    private int refreshRate;
    private int size;
    private boolean hdr;
    private int soundOutput;
    private String speakerSystem;
    private boolean surroundSound;
    private int hdmiMaxRefreshRate;
    private String vesaStandard;
    private int averagePowerConsumption;
    private String energyEfficiency;

    public TV(String name, String model, String resolution, int refreshRate, int size, boolean hdr, int soundOutput, String speakerSystem, boolean surroundSound, int hdmiMaxRefreshRate, String vesaStandard, int averagePowerConsumption, String energyEfficiency) {
        this.name = name;
        this.model = model;
        this.resolution = resolution;
        this.refreshRate = refreshRate;
        this.size = size;
        this.hdr = hdr;
        this.soundOutput = soundOutput;
        this.speakerSystem = speakerSystem;
        this.surroundSound = surroundSound;
        this.hdmiMaxRefreshRate = hdmiMaxRefreshRate;
        this.vesaStandard = vesaStandard;
        this.averagePowerConsumption = averagePowerConsumption;
        this.energyEfficiency = energyEfficiency;
    }


}