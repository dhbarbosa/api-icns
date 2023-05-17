package com.br.icns.api.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProdutosDTO(
        @NotBlank
        String nomeProduto,

        @NotBlank
        LocalDateTime dataVencimento,
        @NotBlank
        String descricao,

        @NotNull
        Double valorUnitario,

        @Valid
        NotasDTO notasDTO
) {

}
