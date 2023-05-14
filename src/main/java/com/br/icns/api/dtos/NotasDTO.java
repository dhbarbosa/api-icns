package com.br.icns.api.dtos;
import jakarta.validation.constraints.*;
import org.aspectj.weaver.ast.Not;

public record NotasDTO(
        @NotNull(message = "O valor total não pode ser vazio")
        Double totalValue,

        @Size(min = 44, max = 44, message = "A chave deve ter o valor min/max de 44 digitos")
        String keyNota
){
        public NotasDTO(Double totalValue, String keyNota){
                this.totalValue =totalValue;
                this.keyNota= keyNota.replace(" ","");

        }
}
