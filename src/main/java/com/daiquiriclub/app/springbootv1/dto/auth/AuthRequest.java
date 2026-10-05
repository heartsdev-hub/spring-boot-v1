package com.daiquiriclub.app.springbootv1.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Debe ser en formato EMAIL")
        String correo,
        @NotBlank(message = "La contraseña es obligatorio")
        String password
) {
}
