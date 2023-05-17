package com.br.icns.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DescricaoProdutoDTO(
        @NotBlank
        String descricao,
        @NotBlank
        LocalDateTime dataVencimento
) {
}
