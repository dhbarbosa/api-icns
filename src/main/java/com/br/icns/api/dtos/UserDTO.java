package com.br.icns.api.dtos;

import com.br.icns.api.enums.RoleName;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

public record UserDTO(
        @NotBlank(message = "O Username não deve estar em branco!")
        String username,
        @NotBlank(message = "O nome não pode ser vazio")
        String name,
        @NotBlank(message = "A senha não pode ser vazia!")
        String password
) {
    public UserDTO setPassword(String password){
                return new UserDTO(username(), name(),password);
    }

}
