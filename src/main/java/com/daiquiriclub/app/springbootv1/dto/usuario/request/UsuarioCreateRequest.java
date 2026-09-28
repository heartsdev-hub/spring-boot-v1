package com.daiquiriclub.app.springbootv1.dto.usuario.request;

import com.daiquiriclub.app.springbootv1.dto.TipoDocumento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UsuarioCreateRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @NotBlank(message = "El tipo documento es obligatorio")
        TipoDocumento tipoDocumento,
        @NotBlank(message = "Numero de documento")
        String numDocumento,
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo debe es en formato correcto")
        String correo,
        @NotBlank(message = "La contraseña es obligatorio")
        @Min(value = 2,message = "El password debe ser mayor a 2 caracteres")
        String password
) {
}
