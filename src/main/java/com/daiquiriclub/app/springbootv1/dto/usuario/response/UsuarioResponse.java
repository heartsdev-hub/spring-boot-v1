package com.daiquiriclub.app.springbootv1.dto.usuario.response;

import com.daiquiriclub.app.springbootv1.dto.TipoDocumento;

import java.time.LocalDate;
import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nombre,
        TipoDocumento tipoDocumento,
        String numDocumento,
        String correo,
        boolean active,
        LocalDate createdAt,
        LocalDate updatedAt
) {
}
