package com.daiquiriclub.app.springbootv1.dto.category.response;

import java.time.LocalDate;
import java.util.UUID;

public record CategoryResponse (
        UUID id,
        String name,
        boolean active,
        LocalDate created_at,
        LocalDate updated_at
) {
}
