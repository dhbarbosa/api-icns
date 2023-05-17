package com.br.icns.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="produtos")
public class Produtos implements Serializable {

    @Serial
    private static final long serialVersionUID= 1L;

    @Column(name = "nomeProduto")
    private String nomeProduto;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idProdutos;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "dataVencimento")
    private LocalDateTime dataVencimento;

    @Column(name = "valorUnitario")
    private Double valorUnitario;

    @ManyToOne
    private Notas notas;

}
