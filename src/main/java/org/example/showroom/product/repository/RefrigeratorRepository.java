package org.example.showroom.product.repository;

import org.example.showroom.product.entity.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {
    Optional<Refrigerator> findByProductName(String productName);
}