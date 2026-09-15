package com.daiquiriclub.app.springbootv1.repository;

import com.daiquiriclub.app.springbootv1.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    boolean existsByName (String name);
}
