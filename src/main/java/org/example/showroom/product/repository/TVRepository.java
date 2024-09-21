package org.example.showroom.product.repository;

import org.example.showroom.product.entity.TV;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TVRepository extends JpaRepository<TV, Long> {
    TV findByProductName(String ProductName);
}