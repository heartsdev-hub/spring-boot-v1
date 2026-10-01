package com.daiquiriclub.app.springbootv1.dto.usuario.response;

import com.daiquiriclub.app.springbootv1.dto.TipoDocumento;
import com.daiquiriclub.app.springbootv1.security.enums.Rol;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nombre,
        TipoDocumento tipoDocumento,
        String numDocumento,
        String correo,
        boolean active,
        Set<Rol> roles,
        LocalDate createdAt,
        LocalDate updatedAt
) {
}
