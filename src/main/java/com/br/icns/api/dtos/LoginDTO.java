package com.br.icns.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record LoginDTO(
        @NotBlank
        @Length(max = 14)
        @Pattern(regexp ="(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
        String username,
        @NotBlank String password) {
}
