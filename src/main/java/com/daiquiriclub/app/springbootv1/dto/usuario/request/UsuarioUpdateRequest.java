package com.daiquiriclub.app.springbootv1.dto.usuario.request;

import com.daiquiriclub.app.springbootv1.dto.TipoDocumento;
import jakarta.validation.constraints.*;

public record UsuarioUpdateRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @NotNull(message = "El tipo documento es obligatorio")
        TipoDocumento tipoDocumento,
        @NotBlank(message = "Numero de documento")
        String numDocumento,
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo debe es en formato correcto")
        String correo,
        @NotBlank(message = "La contraseña es obligatorio")
        @Size(min = 2, message = "Debe ser mayor de 2 caracteres.")
        String password
) {
}