package com.br.icns.api.dtos;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.aspectj.weaver.ast.Not;

import java.util.List;

public record NotasDTO(
        @NotNull(message = "O valor total não pode ser vazio")
        Double totalValue,

        @Size(min = 44, max = 44, message = "A chave deve ter o valor min/max de 44 digitos")
        String keyNota,

        @Valid
        EmpresasDTO empresa,

        @Valid
        List<ProdutosDTO> produtos
){
        public NotasDTO(Double totalValue, String keyNota, EmpresasDTO empresa, List<ProdutosDTO> produtos){
                this.totalValue=totalValue;
                this.keyNota= keyNota.replace(" ","");
                this.empresa=empresa;
                this.produtos = produtos;
        }
}
