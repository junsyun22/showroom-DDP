package org.example.showroom.product.repository;

import org.example.showroom.product.entity.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {
    Optional<Refrigerator> findByProductName(String productName);
}