package com.br.icns.api.models;

import com.br.icns.api.dtos.EmpresasDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empresas")
public class Empresas {

    @Id
    private Long cnpj;

    @Column(name = "nomeFantasia", nullable = false)
    private String nomeFantasia;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="contato", nullable = false)
    private Long contato;

}
