package com.daiquiriclub.app.springbootv1.repository;

import com.daiquiriclub.app.springbootv1.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, UUID> {
}
