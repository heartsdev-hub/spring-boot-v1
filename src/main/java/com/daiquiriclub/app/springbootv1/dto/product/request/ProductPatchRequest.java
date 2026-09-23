package com.daiquiriclub.app.springbootv1.dto.product.request;

import java.math.BigDecimal;

public record ProductPatchRequest(
        String name,
        BigDecimal price,
        Integer stock,
        String categoryId
) {
}
