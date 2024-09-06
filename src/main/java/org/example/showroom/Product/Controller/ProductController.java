package org.example.showroom.Product.Controller;

import org.example.showroom.Product.AirConditioner;
import org.example.showroom.Product.Refrigerator.Refrigerator;
import org.example.showroom.Product.TV.TV;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // 에어컨 데이터 반환
    @GetMapping("/airconditioners")
    public List<AirConditioner> getAirConditioners() {
        return Arrays.asList(
                new AirConditioner("CoolAir X500", "X500AC", "50㎡", "2"),
                new AirConditioner("CoolBreeze 200", "CB200", "30㎡", "1")
        );
    }

    // 냉장고 데이터 반환
    @GetMapping("/refrigerators")
    public List<Refrigerator> getRefrigerators() {
        return Arrays.asList(
                new Refrigerator("FrostFree 300", "RF300", 300, 200, 100, "3", 150, "USA", 2022),
                new Refrigerator("CoolStore 400", "CS400", 400, 250, 150, "2", 180, "South Korea", 2021)
        );
    }

    // TV 데이터 반환
    @GetMapping("/tvs")
    public List<TV> getTVs() {
        return Arrays.asList(
                new TV("VisionPro 55", "VP55", "4K", 120, 55, true, 40, "2.1", true, 120, "VESA 400x400", 80, "2"),
                new TV("BrightView 65", "BV65", "8K", 60, 65, true, 50, "5.1", true, 60, "VESA 600x400", 100, "1")
        );
    }
}
