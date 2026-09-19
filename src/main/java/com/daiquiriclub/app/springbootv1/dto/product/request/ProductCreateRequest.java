package com.daiquiriclub.app.springbootv1.dto.product.request;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductCreateRequest(
        String name,
        BigDecimal price,
        int stock,
        UUID categoryId
) {
}
