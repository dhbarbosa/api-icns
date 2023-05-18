package com.br.icns.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record LoginDTO(
        @NotBlank(message = "Não pode está com usuario vazio")
        String username,
        @NotBlank(message = "Não pode está com usuario vazio")
        String password
) {
}
