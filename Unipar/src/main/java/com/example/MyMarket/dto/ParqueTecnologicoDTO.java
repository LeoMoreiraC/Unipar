package com.example.MyMarket.dto;

import com.example.MyMarket.domain.model.Processador;
import com.example.MyMarket.domain.model.Setor;
import com.example.MyMarket.domain.model.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParqueTecnologicoDTO {
    private Long patrimonio;
    private Processador processador;
    private String geracao;
    private Setor setor;
    private Long ram;
    private String hd;
    private StatusEnum status;
    private LocalDateTime dataAlteracao;
}
