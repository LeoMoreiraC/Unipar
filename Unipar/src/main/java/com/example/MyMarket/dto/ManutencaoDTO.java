package com.example.MyMarket.dto;

import com.example.MyMarket.domain.model.ParqueTecnologico;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManutencaoDTO {
    private Long id;
    @NotNull
    @NotBlank
    private String descricao;
    private Long patrimonio;
    private String status;
    private LocalDateTime dataCadastro;
}
