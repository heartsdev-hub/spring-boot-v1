package com.daiquiriclub.app.springbootv1.dto.Proveedor.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProveedorUpdateRequest (
        @NotBlank(message = "Se require el nombre")
        String nombre,
        @NotBlank(message = "Se require el ruc")
        String ruc,
        @NotBlank(message = "Se require el telefono")
        String telefono,
        @NotBlank(message = "Se require el correo")
        @Email(message = "Debe ser en formato email")
        String correo,
        @NotBlank(message = "Se require la direccion")
        String direccion
) {
}
