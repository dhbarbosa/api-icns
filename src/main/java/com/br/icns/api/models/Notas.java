package com.br.icns.api.models;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notas")
public class Notas implements Serializable {

    @Serial
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idNotas;

    @Column(name = "totalValue", nullable = false)
    private Double totalValue;

    @ManyToOne
    @JoinColumn(name="cnpj")
    private Empresas empresas;

    @Column(name = "dateReceived", nullable = false)
    private LocalDateTime dateReceived;

    @Column(name="dataLastUpdate")
    private LocalDateTime dataLastUpdate;

    @Column(name = "keyNota", nullable = false, unique = true)
    private String keyNota;

    @ManyToOne
    @JoinColumn(name="idUser")
    private User receivedBy;

    public Notas(Double totalValue, String keyNota,User receivedBy, Empresas empresas,LocalDateTime localDateTime){
        this.totalValue = totalValue;
        this.keyNota = keyNota;
        this.receivedBy = receivedBy;
        this.dateReceived = localDateTime;
        this.empresas= empresas;
    }
}
