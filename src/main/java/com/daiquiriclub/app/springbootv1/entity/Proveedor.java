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
@Table(name = "Proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nombre;
    private String ruc;
    private String telefono;
    @Column(unique = true)
    private String correo;
    private String direccion;
    private boolean active = true;
    @Column(updatable = false)
    private LocalDate createAt;
    private LocalDate updatedAt;
    @PrePersist
    public void onCreate(){
        this.createAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }
    @PreUpdate
    public void onUpdate(){
        this.updatedAt = LocalDate.now();
    }
}
