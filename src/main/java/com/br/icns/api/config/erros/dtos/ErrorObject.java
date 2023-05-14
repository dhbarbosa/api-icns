package com.br.icns.api.config.erros.dtos;

public record ErrorObject(
        String message,
        String field,
        Object parameter) {
}
