package com.daiquiriclub.app.springbootv1.dto.category.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryUpdateRequest(
        @NotBlank(message = "The category is required")
        String name
) {
}
