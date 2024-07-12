package com.gl.repository;

import com.gl.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 3. Find Products by Price Range
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
