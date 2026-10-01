package com.daiquiriclub.app.springbootv1.entity;

import com.daiquiriclub.app.springbootv1.dto.TipoDocumento;
import com.daiquiriclub.app.springbootv1.security.enums.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nombre;
    private TipoDocumento tipoDocumento;
    private String numDocumento;
    private String correo;
    private String password;
    private boolean active = true;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Set<Rol> roles = new HashSet<>();
    @Column(updatable = false)
    private LocalDate createdAt;
    private LocalDate updatedAt;
    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }
    @PreUpdate
    public void onUpdate(){
        this.updatedAt = LocalDate.now();
    }
}
