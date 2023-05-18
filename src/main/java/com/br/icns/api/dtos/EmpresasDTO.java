package com.br.icns.api.dtos;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;


public record EmpresasDTO(

        @NotNull(message = "O valor do campo, não pode ser vazio")
        Long cnpj,
        @NotBlank(message = "O valor do campo, não pode ser vazio")
        String nomeFantasia,

        @NotBlank(message = "O valor do campo, não pode ser vazio")
        @Email(message = "Esse campo de seguir um padrão de email")
        String email,

        @NotNull(message = "O valor do campo, não pode ser vazio")
        Long contato
) {
}
