package com.daiquiriclub.app.springbootv1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private BigDecimal price;
    private int stock;
    @ManyToMany
    private Category category;
    @Column(updatable = false)
    private LocalDate created_at;
    private LocalDate updated_at;
    @PrePersist
    public void onCreate(){
        this.created_at = LocalDate.now();
        this.updated_at = LocalDate.now();
    }
    @PreUpdate
    public void onUpdate(){
        this.updated_at = LocalDate.now();
    }
}
