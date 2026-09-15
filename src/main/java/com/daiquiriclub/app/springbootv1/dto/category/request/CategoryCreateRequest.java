package com.daiquiriclub.app.springbootv1.dto.category.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryCreateRequest (
        @NotBlank(message = "La categoria es obligatorio.")
        String name
){
}
