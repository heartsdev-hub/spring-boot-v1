package com.daiquiriclub.app.springbootv1.dto.product.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        BigDecimal price,
        int stock,
        String category,
        LocalDate created_at,
        LocalDate updated_at
) {
}
