package com.daiquiriclub.app.springbootv1.dto.product.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductCreateRequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01",message = "Price must be greater than 0")
        BigDecimal price,
        @Min(value = 0, message = "Stock cannot be negative")
        int stock,
        @NotNull(message = "Category ID is required")
        String categoryId
) {
}
