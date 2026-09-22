package com.daiquiriclub.app.springbootv1.repository;

import com.daiquiriclub.app.springbootv1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByName(String name);
    List<Product> findByActiveTrue();
}
