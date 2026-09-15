package com.daiquiriclub.app.springbootv1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String name;
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
