package com.daiquiriclub.app.springbootv1.dto.Proveedor.Response;

import java.time.LocalDate;
import java.util.UUID;

public record ProveedorResponse (
        UUID id,
        String nombre,
        String ruc,
        String telefono,
        String correo,
        String direccion,
        boolean active,
        LocalDate createdAt,
        LocalDate updatedAt
) {
}
