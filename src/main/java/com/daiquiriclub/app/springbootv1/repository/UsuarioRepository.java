package com.daiquiriclub.app.springbootv1.repository;

import com.daiquiriclub.app.springbootv1.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    @Query("""
    SELECT u FROM usuario u WHERE active= true
    """)
    List<Usuario> allUsuariosActive();
    boolean existsByCorreo(String correo);
}
