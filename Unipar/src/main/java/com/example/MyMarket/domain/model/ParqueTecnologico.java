package com.example.MyMarket.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParqueTecnologico {
    @Id
    @Column
    private Long patrimonio;
    @ManyToOne
    private Processador processador;
    @Column
    private String geracao;
    @ManyToOne
    private Setor setor;
    @Column
    private Long ram;
    @Column
    private String hd;
    @Column
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @Column
    private LocalDateTime dataAlteracao;
}
