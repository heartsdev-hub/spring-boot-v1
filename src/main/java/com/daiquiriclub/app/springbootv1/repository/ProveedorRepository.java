package com.daiquiriclub.app.springbootv1.repository;

import com.daiquiriclub.app.springbootv1.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, UUID> {
    List<Proveedor> findByActiveTrue();
    boolean existsByRuc(String ruc);
    boolean existsByCorreo(String correo);
    @Query("""
        SELECT COUNT(p) > 0
        FROM Proveedor p
        WHERE p.ruc = :ruc
        AND p.id <> :id
    """)
    boolean existsRucInAnotherProveedor(
            @Param("ruc") String ruc,
            @Param("id") UUID id
    );
    @Query(
            """
    SELECT COUNT(p) > 0
    FROM Proveedor p
    WHERE p.correo = :correo
    AND p.id <> :id
"""
    )
    boolean existsCorreoInAnotherProveedor(
            @Param("correo") String correo,
            @Param("id") UUID id
    );
}
