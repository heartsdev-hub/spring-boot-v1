package com.daiquiriclub.app.springbootv1.dto;

public record ApiResult <T> (
        boolean success,
        String message,
        T data
) {
}
