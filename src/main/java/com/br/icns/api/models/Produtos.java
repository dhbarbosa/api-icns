package com.br.icns.api.models;

import com.br.icns.api.dtos.ProdutosDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
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

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idProdutos;

    @Column(name = "nomeProduto")
    private String nomeProduto;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "dataVencimento")
    private LocalDate dataVencimento;

    @Column(name = "codProduto")
    private String codProduto;

    @Column(name = "valorUnitario")
    private Double valorUnitario;

    @ManyToOne
    @JoinColumn(name = "idNotas")
    private Notas notas;

    public Produtos(ProdutosDTO produtosDTO){
        this.nomeProduto=produtosDTO.nomeProduto();
        this.codProduto=produtosDTO.codProduto();
        this.descricao=produtosDTO.descricao();
        this.valorUnitario=produtosDTO.valorUnitario();
        this.dataVencimento=produtosDTO.dataVencimento();
    }
}
